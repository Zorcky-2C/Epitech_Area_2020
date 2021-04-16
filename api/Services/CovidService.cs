using System;
using System.Collections.Generic;
using System.Net;
using Newtonsoft.Json;
using RestSharp;
using WebApi.Helpers;
using WebApi.Models.Services;

namespace WebApi.Services
{
    public interface ICovidService
    {
        public string getCovidGlobalInfo();
        public string getWebCovidhistory();
        public string getWebCovidGlobalInfo();
        public string getAllcountriesInfo();
        public string FindInfoByCountry(string countryCode);
    }

    public class CovidService : ICovidService
    {
        public string getCovidGlobalInfo()
        {
            string url = "https://corona.lmao.ninja/v2/all";

            var client = new RestClient(url);

            var request = new RestRequest("", Method.GET);
            request.AddHeader("Content-Type", "application/json");

            var response = client.Execute(request);

            return response.Content;
        }
        public string getWebCovidhistory()
        {
            string url = "https://disease.sh/v3/covid-19/historical/all?lastdays=120";

            var client = new RestClient(url);

            var request = new RestRequest("", Method.GET);
            request.AddHeader("Content-Type", "application/json");

            var response = client.Execute(request);

            return response.Content;
        }
        public string getWebCovidGlobalInfo()
        {
            string url = "https://disease.sh/v3/covid-19/all";

            var client = new RestClient(url);

            var request = new RestRequest("", Method.GET);
            request.AddHeader("Content-Type", "application/json");

            var response = client.Execute(request);

            return response.Content;
        }
        public string getAllcountriesInfo()
        {
            string url = "https://disease.sh/v3/covid-19/countries";

            var client = new RestClient(url);

            var request = new RestRequest("", Method.GET);
            request.AddHeader("Content-Type", "application/json");

            var response = client.Execute(request);

            return response.Content;
        }
        public string FindInfoByCountry(string countryCode)
        {
            string lien = "https://disease.sh/v3/covid-19/countries/{0}";
            string url = String.Format(lien, countryCode);

            var client = new RestClient(url);

            var request = new RestRequest("", Method.GET);
            request.AddHeader("Content-Type", "application/json");

            var response = client.Execute(request);

            return response.Content;
        }

    }
}