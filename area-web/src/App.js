import React, {useState, useEffect } from 'react';
import './App.css';
import Home from './Pages/Home/Home'
import Header from './Components/Header/Header'
import Footer from './Components/Footer/Footer'
import SignInOutContainer from './Pages/Login/SignInOutContainer'
import Imgur from './Pages/Imgur/container/ImgurApp';
import Splashscreen from './Components/Splashscreen/Splashscreen'
import Cinema from './Pages/Cinema/Cinema';
import Weather from './Pages/Weather/Weather';
import Covid from './Pages/Covid/Covid';
import Youtube from './Pages/Youtube/App'
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";

import Spotify from './Pages/Spotify/src/containers/App/App';

import Facebook from './Pages/Facebook/fb/Facebook';
function App() {
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    setLoading(true);
    setTimeout(() => {
      setLoading(false);
    }, 3000);
  }, []);
  
  return (
    <div className="App">
      { loading ? (
        <Splashscreen/>
        ) : (
        <Router>
        <Header />
        <Switch>
        <Route path="/Spotify">
            <Spotify />
          </Route>
        <Route path="/covid">
            <Covid />
          </Route>
        <Route path="/weather">
            <Weather />
          </Route>
        <Route path="/cinema">
            <Youtube />
          </Route>
        <Route path="/facebook">
            <Facebook />
          </Route>
        <Route path="/imgur">
            <Imgur />
          </Route>
          <Route path="/login">
            <SignInOutContainer path="/login" component={SignInOutContainer} />
          </Route>
          <Route path="/">
            <Home />
          </Route>
        </Switch>
        
        <Footer />
      </ Router>
      )
      }
    </div>
  );
}

export default App;
