package com.example.demo.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.demo.model.LocationStats;

@Service
public class DataService {
	
	private String dataUrl = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	private List <LocationStats> allStats = new ArrayList<>();
	
	
	
	
	public List<LocationStats> getAllStats() {
		return allStats;
	}




	@PostConstruct
	@Scheduled(cron = "* * 1 * * *")
	public void fetchVirusData() throws IOException, InterruptedException {
		
		 List <LocationStats> newstats = new ArrayList<>();
		 int counter=0;

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(dataUrl)).build();
		
		HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
		
		StringReader csvBodyReader = new StringReader(httpResponse.body());
		
		BufferedReader reader = null;
		String line  = "";
		reader  = new BufferedReader(new StringReader(httpResponse.body()));
		while((line = reader.readLine())!=null)
		{
			
			LocationStats locationStat = new LocationStats();
			
			String[] row = line.split(",");
			
//			for(String index : row)
//			{
//				
//			}
			if(counter!=0)
			{
				locationStat.setState(row[0]);
				locationStat.setCountry(row[1]);
				locationStat.setLatestTotalCases(row[row.length-1]);
				System.out.println(locationStat.getState()+", "+locationStat.getCountry()+", "+locationStat.getLatestTotalCases());
				int latestCases =Integer.parseInt(row[row.length-1]);
				int prevCases = Integer.parseInt(row[row.length-2]);
				locationStat.setDifffromPrevDay(latestCases - prevCases);
				newstats.add(locationStat);
			}
			counter+=1;
		}
		this.allStats = newstats;
		
		
		
	}

}
