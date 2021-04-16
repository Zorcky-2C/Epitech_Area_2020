using System;
using System.Collections.Generic;
using System.Net;
using Newtonsoft.Json;
using RestSharp;
using WebApi.Helpers;
using WebApi.Models.Services;

namespace WebApi.Services
{
    public interface IWeatherService
    {
        public string getWeather(WeatherRequest model);
        public string getWebWeather(string city);
        public string getGeoWeather(double lat, double lon);
    }

    public class WeatherService : IWeatherService
    {
        public string getWeather(WeatherRequest model)
        {
            string lien = "https://api.openweathermap.org/data/2.5/weather?q={0}&appid=a6f41d947e0542a26580bcd5c3fb90ef&units=metric";
            string url = String.Format(lien, model.city);

            var client = new RestClient(url);

            var request = new RestRequest("", Method.GET);
            request.AddHeader("Content-Type", "application/json");

            var response = client.Execute(request);

            return response.Content;
        }
        public string getWebWeather(string city)
        {
            string lien = "https://api.openweathermap.org/data/2.5/weather?q={0}&units=metric&appid=0ebf0e29926cc939f557a936228e1129";
            string url = String.Format(lien, city);

            var client = new RestClient(url);
            var request = new RestRequest("", Method.GET);
            request.AddHeader("Content-Type", "application/json");

            var response = client.Execute(request);
            return response.Content;
        }

        public string getGeoWeather(double lat, double lon)
        {
            string lien = "https://api.openweathermap.org/data/2.5/weather?lat={0}&lon={1}&units=metric&appid=0ebf0e29926cc939f557a936228e1129";
            string url = String.Format(lien, lat, lon);

            var client = new RestClient(url);
            var request = new RestRequest("", Method.GET);
            request.AddHeader("Content-Type", "application/json");

            var response = client.Execute(request);
            return response.Content;
        }
    }
}