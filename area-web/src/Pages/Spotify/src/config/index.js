export const config = {
  spotify: {
    authorizationURL: 'https://accounts.spotify.com/authorize',
    clientId: '9219948ba3ca40d793483b839e8849d3',
    redirectUrl: `${window.location.origin}/Spotify/authorize`,
    webAPI: 'https://api.spotify.com/v1',
    scopes: [
      'user-read-email',
      'user-read-private',
      'streaming'
    ]
  }
}
