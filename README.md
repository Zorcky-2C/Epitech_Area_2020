# Epitech Area

<img src="https://github.com/EpitechIT2020/B-YEP-500-BDX-5-1-area-olivier.gurses/blob/380ce8acdde656129446e14c18a245e700b6499f/images/loading_page.png" width="30%" height="30%">              <img src="https://github.com/EpitechIT2020/B-YEP-500-BDX-5-1-area-olivier.gurses/blob/380ce8acdde656129446e14c18a245e700b6499f/images/home_page.png" width="30%" height="30%">              <img src="https://github.com/EpitechIT2020/B-YEP-500-BDX-5-1-area-olivier.gurses/blob/380ce8acdde656129446e14c18a245e700b6499f/images/login_page.png" width="30%" height="30%">


## Introduction 👋

Epitech Area is a web/mobile application developed with React.js and java that allows you to quickly visualize your information according to your subscribed services.

## Prerequisites
- [Docker](https://docs.docker.com/docker-for-windows/install/)
- [ASP.NET Core v3.1](https://docs.microsoft.com/fr-fr/dotnet/core/install/windows?tabs=netcore31)
- [Node](https://nodejs.org/en/download/)
- [React](https://fr.reactjs.org/docs/getting-started.html)
- [Android Studio](https://developer.android.com/studio)
- [Java8](https://www.oracle.com/fr/java/technologies/javase/javase-jdk8-downloads.html)

### How to launch the API ? 🔮
<br>
The API is contained in Docker.
Launch the server locally using Docker

```bash
sudo docker-compose build && sudo docker-compose up
```

## Usage - web app
```
Once the server is launched connect to [localhost:8081](https://localhost:8081/)
```
## Usage - mobile app

```Java
Run the Area mobile app from Android Studio!
```

## Services & Widgets
### Imgur

Allows you to get quickly search for images on Imgur servers

### Facebook

Allows you to get information about your Github account

- Post -> Post a Facebook status to your profile

### Cinema

Allows you to watch some videos on Youtube

- search bar -> Searches for Youtube videos
- viewer -> a widget that allows you to watch a Youtube video
- recommended -> Display 5 recommended Youtube video related to your actual video

### Weather

Displays the current weather

- current weather -> Displays the current temperature and weather
- sear bar -> Allows you to search a city/country

### Covid-19 tracker

Displays Covid-19 related infos such as Covid-19 cases, recovered cases and death

- world map -> Displays the world map to visualize current infected, recovered and death cases per country
- Live Cases by Country -> Array showing the number of Covid-19 infected per country
- Graph -> Graphic showing new infected, recovered or death cases per days worldwide

### Spotify

Listen to Spotify musics

- categories -> Sort the musics into genders and subgenders
- play -> click a title to listen that music

## Api documentation ? 📚

You will find REST API documentation via this link:
```http://127.0.0.1:8080/swagger/index.html ```
