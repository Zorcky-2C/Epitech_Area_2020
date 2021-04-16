using System;
using System.Collections.Generic;
using System.Net;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using RestSharp;
using WebApi.Helpers;
using WebApi.Models.Services;

namespace WebApi.Services
{
    public interface ISpotifyService
    {
        public string addToPlaylist(addToPlaylistRequest model);
        public string openPlaylists(openPlaylistRequest model);
        public string searchSongs(searchSongsRequest model);
        public string addTrackToPlaylist(addTrackToPlaylistRequest model);
        public string removeTrackFromPlaylist(removeTrackFromPlaylistRequest model);
        public string changeNamePlaylist(changeNamePlaylistRequest model);
        public string goToEditPlaylist(goToEditPlaylistRequest model);
        public string goToNewPlaylist(goToNewPlaylistRequest model);
        public string removePlaylist(removePlaylistRequest model);
        public string getUserId(getUserIdRequest model);
        public string addNewPlaylist(addNewPlaylistRequest model);
        public string WebSpotifyCategories(string auth);
        public string WebCategoryPlaylists(string auth, string category_id);
        public string WebPlaylistTracks(string auth, string playlist_id);
        public string WebUserProfile(string auth);
    }

    public class SpotifyService : ISpotifyService
    {
        public string addToPlaylist(addToPlaylistRequest model)
        {
            string url = "https://api.spotify.com/v1/me/playlists";

            var client = new RestClient(url);
            var request = new RestRequest("", Method.GET);
            request.AddHeader("Content-Type", "application/json");
            request.AddHeader("Authorization", model.authorization);
            var response = client.Execute(request);

            return response.Content;
        }
        public string openPlaylists(openPlaylistRequest model)
        {
            string url = "https://api.spotify.com/v1/me/playlists";

            var client = new RestClient(url);
            var request = new RestRequest("", Method.GET);
            request.AddHeader("Content-Type", "application/json");
            request.AddHeader("Authorization", model.authorization);
            var response = client.Execute(request);

            return response.Content;
        }
        public string searchSongs(searchSongsRequest model)
        {
            string lien = "https://api.spotify.com/v1/search?q={0}{1}";
            string url = String.Format(lien, model.name, model.type);

            var client = new RestClient(url);

            var request = new RestRequest("", Method.GET);
            request.AddHeader("Content-Type", "application/json");
            request.AddHeader("Authorization", model.authorization);

            var response = client.Execute(request);

            return response.Content;
        }
        public string addTrackToPlaylist(addTrackToPlaylistRequest model)
        {
            string lien = "https://api.spotify.com/v1/playlists/{0}{1}";
            string url = String.Format(lien, model.currentPlaylistId, model.param);

            String[] tracks = new String[] { model.uris };

            var client = new RestClient(url);

            var request = new RestRequest("", Method.POST);
            request.AddHeader("Content-Type", "application/json");
            request.AddHeader("Authorization", model.authorization);
            request.AddJsonBody(tracks);

            var response = client.Execute(request);

            return response.Content;
        }
        public string removeTrackFromPlaylist(removeTrackFromPlaylistRequest model)
        {
            string lien = "https://api.spotify.com/v1/playlists/{0}{1}";
            string url = String.Format(lien, model.currentPlaylistId, model.param);

            var client = new RestClient(url);

            var request = new RestRequest("", Method.DELETE);
            request.AddHeader("Content-Type", "application/json");
            request.AddHeader("Authorization", model.authorization);
            request.AddJsonBody("tracks", model.tracks);

            var response = client.Execute(request);

            return response.Content;
        }
        public string changeNamePlaylist(changeNamePlaylistRequest model)
        {
            string lien = "https://api.spotify.com/v1/playlists/{0}";
            string url = String.Format(lien, model.currentPlaylistId);

            var client = new RestClient(url);

            var request = new RestRequest("", Method.PUT);
            request.AddHeader("Content-Type", "application/json");
            request.AddHeader("Authorization", model.authorization);
            request.AddJsonBody("name", model.name);

            var response = client.Execute(request);

            return response.Content;
        }
        public string goToEditPlaylist(goToEditPlaylistRequest model)
        {
            string lien = "https://api.spotify.com/v1/playlists/{0}{1}";
            string url = String.Format(lien, model.playlistId, model.param);

            var client = new RestClient(url);

            var request = new RestRequest("", Method.GET);
            request.AddHeader("Content-Type", "application/json");
            request.AddHeader("Authorization", model.authorization);

            var response = client.Execute(request);

            return response.Content;
        }
        public string goToNewPlaylist(goToNewPlaylistRequest model)
        {
            string url = "https://api.spotify.com/v1/me/tracks";

            var client = new RestClient(url);

            var request = new RestRequest("", Method.GET);
            request.AddHeader("Content-Type", "application/json");
            request.AddHeader("Authorization", model.authorization);

            var response = client.Execute(request);

            return response.Content;
        }
        public string removePlaylist(removePlaylistRequest model)
        {
            string lien = "https://api.spotify.com/v1/playlists/{0}{1}";
            string url = String.Format(lien, model.id, model.param);

            var client = new RestClient(url);

            var request = new RestRequest("", Method.DELETE);
            request.AddHeader("Content-Type", "application/json");
            request.AddHeader("Authorization", model.authorization);

            var response = client.Execute(request);

            return response.Content;
        }
        public string getUserId(getUserIdRequest model)
        {
            string url = "https://api.spotify.com/v1/me";

            var client = new RestClient(url);

            var request = new RestRequest("", Method.GET);
            request.AddHeader("Content-Type", "application/json");
            request.AddHeader("Authorization", model.authorization);

            var response = client.Execute(request);

            return response.Content;
        }
        public string addNewPlaylist(addNewPlaylistRequest model)
        {
            string lien = "https://api.spotify.com/v1/users/{0}{1}";
            string url = String.Format(lien, model.userId, model.param);

            var client = new RestClient(url);

            var request = new RestRequest("", Method.POST);
            request.AddHeader("Content-Type", "application/json");
            request.AddHeader("Authorization", model.authorization);
            request.AddJsonBody("name", model.name);

            var response = client.Execute(request);

            return response.Content;
        }
        public string WebSpotifyCategories(string auth)
        {
            string url = "https://api.spotify.com/v1/browse/categories?country=FR&locale=pt_FR";

            var client = new RestClient(url);

            var request = new RestRequest("", Method.GET);
            request.AddHeader("Content-Type", "application/json");
            request.AddHeader("Authorization", auth);

            var response = client.Execute(request);

            return response.Content;
        }
        public string WebCategoryPlaylists(string auth, string category_id)
        {
            string lien = "https://api.spotify.com/v1/browse/categories/{0}/playlists";
            string url = String.Format(lien, category_id);

            var client = new RestClient(url);

            var request = new RestRequest("", Method.GET);
            request.AddHeader("Content-Type", "application/json");
            request.AddHeader("Authorization", auth);

            var response = client.Execute(request);

            return response.Content;
        }
        public string WebPlaylistTracks(string auth, string playlist_id)
        {
            string lien = "https://api.spotify.com/v1/playlists/{0}/tracks";
            string url = String.Format(lien, playlist_id);

            var client = new RestClient(url);

            var request = new RestRequest("", Method.GET);
            request.AddHeader("Content-Type", "application/json");
            request.AddHeader("Authorization", auth);

            var response = client.Execute(request);

            return response.Content;
        }
        public string WebUserProfile(string auth)
        {
            string url = "https://api.spotify.com/v1/me";

            var client = new RestClient(url);

            var request = new RestRequest("", Method.GET);
            request.AddHeader("Content-Type", "application/json");
            request.AddHeader("Authorization", auth);

            var response = client.Execute(request);

            return response.Content;
        }
    }

}