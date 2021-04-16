using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using Newtonsoft.Json;
using RestSharp;
using WebApi.Helpers;
using WebApi.Models.Services;
using WebApi.Entities;
using AutoMapper;

namespace WebApi.Services
{
    public interface IImgurService
    {
        const string ClientSecret = "a0eda1484912f8b7ecface2ad225065858e91f00";

        public string getImgurUrl();
        public ImgurResponse GetToken();
        public string HomeUploadImage(HomePageRequest model);

        public string FavUploadImage(FavPageRequest model);
        public string ImgurProfile(ImageProfileRequest model);

        public string getImageUrl();
        public string getFavImageUrl(ImageUrlRequest model);
        /*public void InsertData(ImgurDataRequest model);
        public ImgurResponse getByName(string name);*/


    }

    public class ImgurService : IImgurService
    {

        private readonly DataContext _context;
        private readonly IMapper _mapper;

        public ImgurService(
            DataContext context,
            IMapper mapper
            )
        {
            _context = context;
            _mapper = mapper;
        }

        public string getImgurUrl()
        {

            string url = "https://api.imgur.com/oauth2/authorize?client_id=2479bf1d5fdd677&response_type=token&state=Login";

            var client = new RestClient(url);

            var request = new RestRequest("", Method.GET);
            request.AddHeader("Content-Type", "application/json");

            var response = client.Execute(request);
            Console.WriteLine(response.ResponseUri.ToString());
            return response.ResponseUri.ToString();
        }

        public ImgurResponse GetToken()
        {
            string Url = "https://api.imgur.com/oauth2/token/";
            string DataTemplate = "client_id={0}&client_secret={1}&grant_type=refresh_token";
            string Data = String.Format(DataTemplate, "f906aeb3ce16399", "a0eda1484912f8b7ecface2ad225065858e91f00");

            using (WebClient Client = new WebClient())
            {
                string ApiResponse = Client.UploadString(Url, Data);

                var Response = JsonConvert.DeserializeObject(ApiResponse) as Dictionary<string, object>;

                Console.WriteLine(Convert.ToString(Response["access_token"]));

                return new ImgurResponse()
                {
                    AccessToken = Convert.ToString(Response["access_token"]),
                    RefreshToken = Convert.ToString(Response["refresh_token"]),
                    Username = Convert.ToString(Response["account_username"])
                };
            }
        }

        public string HomeUploadImage(HomePageRequest model)
        {
            string url = "https://api.imgur.com/3/gallery/user/rising/0.json";

            var client = new RestClient(url);

            var request = new RestRequest("", Method.GET);
            request.AddHeader("Content-Type", "application/json");
            request.AddHeader("Authorization", model.authorization);
            request.AddHeader("User-Agent", model.userAgent);

            var response = client.Execute(request);

            return response.Content;
        }

        public string FavUploadImage(FavPageRequest model)
        {
            string lien = "https://api.imgur.com/3/account/{0}{1}";
            string url = String.Format(lien, model.username, model.param);

            var client = new RestClient(url);

            var request = new RestRequest("", Method.GET);
            request.AddHeader("Content-Type", "application/json");
            request.AddHeader("Authorization", model.authorization);
            request.AddHeader("User-Agent", model.userAgent);

            var response = client.Execute(request);

            return response.Content;
        }

        public string ImgurProfile(ImageProfileRequest model)
        {
            string lien = "https://api.imgur.com/3/account/{0}";
            string url = String.Format(lien, model.username);

            var client = new RestClient(url);

            var request = new RestRequest("", Method.GET);
            request.AddHeader("Content-Type", "application/json");
            request.AddHeader("Authorization", model.authorization);

            var response = client.Execute(request);

            return response.Content;
        }

        public string getImageUrl()
        {
            string url = "https://i.imgur.com/";

            return url;
        }

        public string getFavImageUrl(ImageUrlRequest model)
        {
            string lien = "https://i.imgur.com/{0}{1}";
            string url = String.Format(lien, model.imageId, model.imageExtention);

            return url;
        }

        /*public void InsertData(ImgurDataRequest model)
        {
            var accesstoken = _context.Accounts.FirstOrDefault(x => x.ImgurAccesstoken == null || x.ImgurAccesstoken == model.accessToken);
            var refreshtoken = _context.Accounts.FirstOrDefault(x => x.ImgurRefreshtoken == null || x.ImgurRefreshtoken == model.refreshToken);
            var username = _context.Accounts.FirstOrDefault(x => x.ImgurUsername == null || x.ImgurUsername == model.username);

            _context.Accounts.Update(accesstoken);
            _context.Accounts.Update(refreshtoken);
            _context.Accounts.Update(username);
            _context.SaveChanges();

        }

        public ImgurResponse getByName(string name)
        {
            var imgurdata = getImgurData(name);
            return _mapper.Map<ImgurResponse>(imgurdata);
        }

        private Account getImgurData(string name)
        {
            var account = _context.Accounts.Find(name);
            if (account == null) throw new KeyNotFoundException("Data not found");
            return account;
        }*/

    }

}