using System;
using System.Collections.Generic;
using System.Net;
using Newtonsoft.Json;
using RestSharp;
using WebApi.Helpers;
using WebApi.Models.Services;

namespace WebApi.Services
{
    public interface ICinemaService
    {
        public string topRated();
        public string Popular();
        public string NowPlaying();
        public string Upcoming();
        public string TvAiring();
        public string TvOn();
        public string PopularTv();
        public string RatedTv();
    }

    public class CinemaService : ICinemaService
    {
        public string topRated()
        {
            string url = "https://api.themoviedb.org/3/movie/top_rated?api_key=c8613554cdc2bab5a805f28ece5b8a41";
            return (url);
        }
        public string Popular()
        {
            string url = "https://api.themoviedb.org/3/movie/popular?api_key=c8613554cdc2bab5a805f28ece5b8a41";
            return (url);
        }
        public string NowPlaying()
        {
            string url = "https://api.themoviedb.org/3/movie/now_playing?api_key=c8613554cdc2bab5a805f28ece5b8a41";
            return (url);
        }
        public string Upcoming()
        {
            string url = "https://api.themoviedb.org/3/movie/upcoming?api_key=c8613554cdc2bab5a805f28ece5b8a41";
            return (url);
        }
        public string TvAiring()
        {
            string url = "https://api.themoviedb.org/3/tv/airing_today?api_key=c8613554cdc2bab5a805f28ece5b8a41";
            return (url);
        }
        public string TvOn()
        {
            string url = "https://api.themoviedb.org/3/tv/on_the_air?api_key=c8613554cdc2bab5a805f28ece5b8a41";
            return (url);
        }
        public string PopularTv()
        {
            string url = "https://api.themoviedb.org/3/tv/popular?api_key=c8613554cdc2bab5a805f28ece5b8a41";
            return (url);
        }
        public string RatedTv()
        {
            string url = "https://api.themoviedb.org/3/tv/top_rated?api_key=c8613554cdc2bab5a805f28ece5b8a41";
            return (url);
        }
    }
}