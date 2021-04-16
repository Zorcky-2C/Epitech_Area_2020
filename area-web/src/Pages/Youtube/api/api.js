import axios from 'axios';

const instanse = axios.create({
    //baseURL: 'https://www.googleapis.com/youtube/v3', 
    baseURL: 'http://127.0.0.1:8080'
});

export const getVideos = (query) => {
    return  instanse.post(`Youtube/searchVideos`, { query: query }).then(resp => resp.data);
    //return  instanse.post(`/search?q=${query}`).then(resp => resp.data);
}

export const getTrendsVideos = () => {
    return  instanse.get(`/Youtube/getTrendsVideos`).then(resp => resp.data);
    //eturn  instanse.get(`/videos?chart=mostPopular`).then(resp => resp.data);
}