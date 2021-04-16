import { config } from '../config';

const { spotify } = config;

const defaultOptions = {
  'Accept': 'application/json',
  'Content-Type': 'application/json',
  'method': 'GET',
};

export const endpoints = {
  getAuthorization: {
    url: `http://127.0.0.1:8080/Spotify/WebAuth`,
    options: {
      method: 'GET'
    }
  },
  getCategories: {
    //url: `${spotify.webAPI}/browse/categories?country=FR&locale=pt_FR`,
    url: `http://127.0.0.1:8080/Spotify/getWebCategory`,
    options: defaultOptions,
  },
  getCategoryPlaylists: {
    //url: `${spotify.webAPI}/browse/categories/{categoryId}/playlists`,
    url: `http://127.0.0.1:8080/Spotify/getWebCategoryPlaylists/{categoryId}`,
    options: defaultOptions,
  },
  getPlaylistTracks: {
    //url: `${spotify.webAPI}/playlists/{playlistId}/tracks`,
    url: `http://127.0.0.1:8080/Spotify/getPlaylistTracks/{playlistId}`,
    options: defaultOptions,
  },
  getUserProfile: {
    //url: `${spotify.webAPI}/me`,
    url: `http://127.0.0.1:8080/Spotify/getUserProfile`,
    options: defaultOptions,
  },
}
