package com.arakaru.coronavirus_tracker.service;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import com.arakaru.coronavirus_tracker.country.Country;

@Service
public class Data {
	
	private final static String URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	List<Country> newStats = new ArrayList<>();

	public List<Country> getNewStats() {
		Collections.sort(newStats, (a, b) -> Integer.parseInt(b.getCount()) - Integer.parseInt(a.getCount()));
		return newStats;
	}

	public void setNewStats(List<Country> newStats) {
		this.newStats = newStats;
	}

	@PostConstruct
	public void getData() throws IOException, InterruptedException {

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest req = HttpRequest.newBuilder().uri(URI.create(URL)).build();
		HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());
		StringReader csvBodyReader = new StringReader(resp.body());
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
		for (CSVRecord record : records) {
			Country country = new Country();
			country.setState(record.get(0));
			country.setCountryName(record.get(1));
			country.setCount(record.get(record.size() - 1));

			newStats.add(country);

		}
		// System.out.println(newStats);
	}

}
