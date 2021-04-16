import React, { useState } from 'react';
import './Home.css';
import Banner from '../../Components/Banner/Banner'
import Card from '../../Components/Card/Card'
import { useHistory } from "react-router-dom";
import { Avatar, Button } from "@material-ui/core";

function Home() {
    const history = useHistory();
    const [showImgur, setshowImgur] = useState(true)
    const [showFacebook, setshowFacebook] = useState(true)
    const [showCinema, setshowCinema] = useState(true)
    
    const [showWeather, setshowWeather] = useState(true)
    const [showNews, setshowNews] = useState(true)
    const [showCovid, setshowCovid] = useState(true)
    const [showSpotify, setshowSpotify] = useState(true)

    return (
        <div className='home'>
            <Banner />
            <div className='home__section'>
            {
                showImgur?<Card
                src="https://cdn.freebiesupply.com/images/thumbs/2x/imgur-logo.png"
                title="Imgur"
                description="Description"
                route="imgur"
                /> : null
            }
            <button onClick={()=> setshowImgur(true)}>Show</button>
            <button onClick={()=> setshowImgur(false)}>Hide</button>
            {
                showFacebook?
            <Card
                src="https://cdn.freebiesupply.com/logos/thumbs/2x/facebook-icon-logo.png"
                title="facebook"
                description=""
                route="facebook"
            /> : null
            }
            <button onClick={()=> setshowFacebook(true)}>Show</button>
            <button onClick={()=> setshowFacebook(false)}>Hide</button>
            {
                showCinema?
            <Card
                src="https://objectifsmartphone.fr/wp-content/uploads/2020/06/ios-14-widgets.jpg"
                title="cinema"
                description="."
                route="cinema"
            /> : null
            }
            <button onClick={()=> setshowCinema(true)}>Show</button>
            <button onClick={()=> setshowCinema(false)}>Hide</button>
            
            </div>
            <div className='home__section'>
                { showWeather?
            <Card
                src="https://cdn.dribbble.com/users/915711/screenshots/5827243/weather_icon3.png"
                title="weather"
                description=" "
                route="weather"
            /> : null
                }
            <button onClick={()=> setshowWeather(true)}>Show</button>
            <button onClick={()=> setshowWeather(false)}>Hide</button>
            {
                showCovid?
            <Card
                src="https://www.golfclubdenantes.com/wp-content/uploads/2020/03/logo_coronavirus_redim_800x600_acf_cropped.jpg"
                title="covid"
                description=" "
                route="covid"
            /> : null
            }
            <button onClick={()=> setshowCovid(true)}>Show</button>
            <button onClick={()=> setshowCovid(false)}>Hide</button>
            {
                 showSpotify?
            <Card
                src="https://storage.googleapis.com/pr-newsroom-wp/1/2018/11/Spotify_Logo_RGB_Green.png"
                title="Spotify"
                description=" "
                route="Spotify"
            /> : null
            }
            <button onClick={()=> setshowSpotify(true)}>Show</button>
            <button onClick={()=> setshowSpotify(false)}>Hide</button>
            </div>//https://www.sketchappsources.com/resources/source-image/PayPalCard.png
            
        </div>
    )
}

export default Home
