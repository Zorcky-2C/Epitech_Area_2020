using Microsoft.AspNetCore.Mvc;
using System;
using WebApi.Services;
using System.Net;
using WebApi.Models.Services;

namespace WebApi.Controllers
{
    [ApiController]
    public class ServicesController : Controller
    {
        private readonly IImgurService _imgurService;
        private readonly ISpotifyService _spotifyService;
        private readonly IWeatherService _weatherService;
        private readonly ICovidService _covidService;
        private readonly ICinemaService _cinemaService;
        private readonly IYoutubeService _youtubeService;
        public ServicesController(
            IImgurService imgurService,
            ISpotifyService spotifyService,
            IWeatherService weatherService,
            ICovidService covidService,
            ICinemaService cinemaService,
            IYoutubeService youtubeService)
        {
            _imgurService = imgurService;
            _spotifyService = spotifyService;
            _weatherService = weatherService;
            _covidService = covidService;
            _cinemaService = cinemaService;
            _youtubeService = youtubeService;
        }

        [HttpGet("ImgurAuth/RequestUrl")]
        public IActionResult LoadImgurAuth()
        {
            var authorizationRequest = "https://api.imgur.com/oauth2/authorize?client_id=2479bf1d5fdd677&response_type=token&state=Login";
            return Redirect(authorizationRequest);
        }

        [HttpPost("ImgurAuth/HomePage")]
        public ContentResult HomeUploadImage(HomePageRequest model)
        {
            String result = _imgurService.HomeUploadImage(model);

            return Content(result, "application/json");
        }

        [HttpPost("ImgurAuth/FavPage")]
        public ContentResult FavUploadImage(FavPageRequest model)
        {
            String result = _imgurService.FavUploadImage(model);

            return Content(result, "application/json");
        }

        [HttpGet("ImgurAuth/Image")]
        public string LoadImage()
        {
            var imageUrl = _imgurService.getImageUrl();

            return imageUrl;
        }

        [HttpPost("ImgurAuth/FavImage")]
        public string LoadFavImage(ImageUrlRequest model)
        {
            var imageUrl = _imgurService.getFavImageUrl(model);

            return imageUrl;
        }

        [HttpPost("ImgurAuth/Profile")]
        public ContentResult LoadProfile(ImageProfileRequest model)
        {
            var result = _imgurService.ImgurProfile(model);

            return Content(result, "application/json");
        }

        [HttpPost("ImgurAuth/Data")]
        public ContentResult getData(ImgurDataRequest model)
        {
            var result = model.username;

            return Content(result, "application/json");
        }

        /*[HttpPost("InsertImgurData")]
        public IActionResult InsertImgurData(ImgurDataRequest model)
        {
            if (model.accessToken != null)
            {
                _imgurService.InsertData(model);

                return Ok(new { message = "Insert Imgur user data successed" });
            }
            else
            {
                return Ok(new { message = "Insert Imgur user data faild" });
            }
        }

        [HttpGet("GetImgurData")]
        public ActionResult<ImgurResponse> GetById(string name)
        {
            var account = _imgurService.getByName(name);
            return Ok(account);
        }*/


        [HttpPost("Spotify/addToPlaylist")]
        public ContentResult addToPlaylist(addToPlaylistRequest model)
        {
            var result = _spotifyService.addToPlaylist(model);

            return Content(result, "application/json");
        }

        [HttpPost("Spotify/openPlaylists")]
        public ContentResult openPlaylists(openPlaylistRequest model)
        {
            var result = _spotifyService.openPlaylists(model);

            return Content(result, "application/json");
        }

        [HttpPost("Spotify/searchSongs")]
        public ContentResult searchSongs(searchSongsRequest model)
        {
            var result = _spotifyService.searchSongs(model);

            return Content(result, "application/json");
        }

        [HttpPost("Spotify/removeTrackFromPlaylist")]
        public ContentResult removeTrackFromPlaylist(removeTrackFromPlaylistRequest model)
        {
            var result = _spotifyService.removeTrackFromPlaylist(model);

            return Content(result, "application/json");
        }

        [HttpPost("Spotify/changeNamePlaylist")]
        public ContentResult changeNamePlaylist(changeNamePlaylistRequest model)
        {
            var result = _spotifyService.changeNamePlaylist(model);

            return Content(result, "application/json");
        }


        [HttpPost("Spotify/goToEditPlaylist")]
        public ContentResult goToEditPlaylist(goToEditPlaylistRequest model)
        {
            var result = _spotifyService.goToEditPlaylist(model);

            return Content(result, "application/json");
        }

        [HttpPost("Spotify/goToNewPlaylist")]
        public ContentResult goToNewPlaylist(goToNewPlaylistRequest model)
        {
            var result = _spotifyService.goToNewPlaylist(model);

            return Content(result, "application/json");
        }

        [HttpPost("Spotify/removePlaylist")]
        public ContentResult removePlaylist(removePlaylistRequest model)
        {
            var result = _spotifyService.removePlaylist(model);

            return Content(result, "application/json");
        }

        [HttpPost("Spotify/getUserId")]
        public ContentResult getUserId(getUserIdRequest model)
        {
            var result = _spotifyService.getUserId(model);

            return Content(result, "application/json");
        }

        [HttpPost("Spotify/addNewPlaylist")]
        public ContentResult addNewPlaylist(addNewPlaylistRequest model)
        {
            var result = _spotifyService.addNewPlaylist(model);

            return Content(result, "application/json");
        }

        [HttpPost("Spotify/addTrackToPlaylist")]
        public ContentResult addTrackToPlaylist(addTrackToPlaylistRequest model)
        {
            var result = _spotifyService.addTrackToPlaylist(model);

            return Content(result, "application/json");
        }

        //------web spotify------
        [HttpGet("Spotify/WebAuth")]
        public IActionResult WebAuthentification()
        {
            string redirect_url = "https://accounts.spotify.com/en/login?continue=https:%2F%2Faccounts.spotify.com%2Fauthorize%3Fscope%3Duser-read-email%252Cuser-read-private%252Cstreaming%26response_type%3Dtoken%26redirect_uri%3Dhttps%253A%252F%252Flocalhost%253A8081%252FSpotify%252Fauthorize%26client_id%3D9219948ba3ca40d793483b839e8849d3%26show_dialog%3Dtrue";

            return Redirect(redirect_url);
        }

        [HttpGet("Spotify/getWebCategory")]
        public ContentResult WebSpotifyCategories([FromQuery] accesstokenRequest model)
        {
            var result = _spotifyService.WebSpotifyCategories(model.Authorization);

            return Content(result, "application/json");
        }

        [HttpGet("Spotify/getWebCategoryPlaylists/{category_id}")]
        public ContentResult WebCategoryPlaylists([FromQuery] accesstokenRequest model, string category_id)
        {
            var result = _spotifyService.WebCategoryPlaylists(model.Authorization, category_id);

            return Content(result, "application/json");
        }

        [HttpGet("Spotify/getPlaylistTracks/{playlist_id}")]
        public ContentResult WebPlaylistTracks([FromQuery] accesstokenRequest model, string playlist_id)
        {
            var result = _spotifyService.WebPlaylistTracks(model.Authorization, playlist_id);

            return Content(result, "application/json");
        }

        [HttpGet("Spotify/getUserProfile")]
        public ContentResult WebUserProfile([FromQuery] accesstokenRequest model)
        {
            var result = _spotifyService.WebUserProfile(model.Authorization);

            return Content(result, "application/json");
        }



        [HttpPost("Weather/getWeather")]
        public ContentResult getWeather(WeatherRequest model)
        {
            var result = _weatherService.getWeather(model);

            return Content(result, "application/json");
        }

        [HttpGet("weather/q={city}")]
        public ContentResult getWebWeather(string city)
        {
            var result = _weatherService.getWebWeather(city);

            return Content(result, "application/json");
        }

        [HttpGet("weather/lat={lat}&lon={lon}")]
        public ContentResult getGeoWeather(double lat, double lon)
        {
            var result = _weatherService.getGeoWeather(lat, lon);

            return Content(result, "application/json");
        }

        [HttpGet("Covid/getCovidGlobalInfo")]
        public ContentResult getCovidGlobalInfo()
        {
            var result = _covidService.getCovidGlobalInfo();

            return Content(result, "application/json");
        }

        //------web covid------
        [HttpGet("Covid/getWebCovidhistory")]
        public ContentResult getWebCovidhistory()
        {
            var result = _covidService.getWebCovidhistory();

            return Content(result, "application/json");
        }

        [HttpGet("Covid/getWebCovidGlobalInfo")]
        public ContentResult getWebCovidGlobalInfo()
        {
            var result = _covidService.getWebCovidGlobalInfo();

            return Content(result, "application/json");
        }

        [HttpGet("Covid/getAllcountriesInfo")]
        public ContentResult getAllcountriesInfo()
        {
            var result = _covidService.getAllcountriesInfo();

            return Content(result, "application/json");
        }

        [HttpGet("Covid/FindInfoByCountry/{countryCode}")]
        public ContentResult FindInfoByCountry(string countryCode)
        {
            var result = _covidService.FindInfoByCountry(countryCode);

            return Content(result, "application/json");
        }

        [HttpGet("Cinema/topRated")]
        public ContentResult topRated()
        {
            var result = _cinemaService.topRated();

            return Content(result, "application/json");
        }

        [HttpGet("Cinema/Popular")]
        public ContentResult Popular()
        {
            var result = _cinemaService.Popular();

            return Content(result, "application/json");
        }

        [HttpGet("Cinema/NowPlaying")]
        public ContentResult NowPlaying()
        {
            var result = _cinemaService.NowPlaying();

            return Content(result, "application/json");
        }

        [HttpGet("Cinema/Upcoming")]
        public ContentResult Upcoming()
        {
            var result = _cinemaService.Upcoming();

            return Content(result, "application/json");
        }

        [HttpGet("Cinema/TvAiring")]
        public ContentResult TvAiring()
        {
            var result = _cinemaService.TvAiring();

            return Content(result, "application/json");
        }

        [HttpGet("Cinema/TvOn")]
        public ContentResult TvOn()
        {
            var result = _cinemaService.TvOn();

            return Content(result, "application/json");
        }

        [HttpGet("Cinema/PopularTv")]
        public ContentResult PopularTv()
        {
            var result = _cinemaService.PopularTv();

            return Content(result, "application/json");
        }

        [HttpGet("Cinema/RatedTv")]
        public ContentResult RatedTv()
        {
            var result = _cinemaService.RatedTv();

            return Content(result, "application/json");
        }

        [HttpPost("Youtube/searchVideos")]
        public ContentResult searchVideos(YoutubeRequest model)
        {
            var result = _youtubeService.searchVideos(model);

            return Content(result, "application/json");
        }

        [HttpGet("Youtube/getTrendsVideos")]
        public ContentResult getTrendsVideos()
        {
            var result = _youtubeService.getTrendsVideos();

            return Content(result, "application/json");
        }

        [HttpPost("Facebook/AccessToken")]
        public ContentResult getAccessToken(FacebookRequest model)
        {
            var result = model.accesstoken;
            return Content(result, "application/json");
        }
    }
}

