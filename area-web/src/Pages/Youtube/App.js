import React, {useEffect, useState} from 'react';
import {Grid} from '@material-ui/core';
import SearchBar from "./components/SearchBar";
import {getTrendsVideos, getVideos} from "./api/api";
import CurrentVideo from "./components/CurrentVideo";
import VideoList from "./components/VideoList";

function Youtube() {

    const [videos, setVideos] = useState([]);
    const [selectedVideo, setSelectedVideo] = useState(null);

    const handleSubmit = (query) => {
        getVideos(query).then(data => {
            console.log(data.items)
            setVideos(data.items);
            setSelectedVideo(data.items[0])
        });
    }


    useEffect(() => {
        getTrendsVideos().then(data => {
            console.log(data.items)
            setVideos(data.items);
            setSelectedVideo(data.items[0]);
        })
    }, []);

    return (
        <Grid container justify="center">
            <Grid item xs={12}>
                <Grid container spacing={8}>
                    <Grid item xs={12}>
                        <SearchBar handleSubmit={handleSubmit}/>
                    </Grid>
                    <Grid item xs={8}>
                        <CurrentVideo video={selectedVideo} />
                    </Grid>
                    <Grid item xs={4}>
                        <VideoList videos={videos} setSelectedVideo={setSelectedVideo} />
                    </Grid>
                </Grid>
            </Grid>
        </Grid>
    )
}

export default Youtube;