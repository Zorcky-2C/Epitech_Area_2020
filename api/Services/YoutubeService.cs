using System;
using System.Collections.Generic;
using System.Net;
using Newtonsoft.Json;
using RestSharp;
using WebApi.Helpers;
using WebApi.Models.Services;

namespace WebApi.Services
{
    public interface IYoutubeService
    {
        public string searchVideos(YoutubeRequest model);
        public string getTrendsVideos();
    }

    public class YoutubeService : IYoutubeService
    {
        public string searchVideos(YoutubeRequest model)
        {
            string lien = "https://www.googleapis.com/youtube/v3/search?q={0}";
            string url = String.Format(lien, model.query);

            var client = new RestClient(url);

            var request = new RestRequest("", Method.GET);
            request.AddHeader("Content-Type", "application/json");
            request.AddParameter("part", "snippet");
            request.AddParameter("maxResults", "5");
            request.AddParameter("key", "AIzaSyBIhaQEXcMeJQHtbHZ-UgEuwWIclXinVvM");

            var response = client.Execute(request);

            return response.Content;
        }
        public string getTrendsVideos()
        {
            string url = "https://www.googleapis.com/youtube/v3/videos?chart=mostPopular";

            var client = new RestClient(url);

            var request = new RestRequest("", Method.GET);
            request.AddHeader("Content-Type", "application/json");
            request.AddParameter("part", "snippet");
            request.AddParameter("maxResults", "5");
            request.AddParameter("key", "AIzaSyBIhaQEXcMeJQHtbHZ-UgEuwWIclXinVvM");

            var response = client.Execute(request);

            return response.Content;
        }

    }
}