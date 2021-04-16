using System;
using System.Collections.Generic;

namespace WebApi.Entities
{
    public class Account
    {
        public int Id { get; set; }
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public string Email { get; set; }
        public string PasswordHash { get; set; }
        public string VerificationToken { get; set; }
        public DateTime? Verified { get; set; }
        public bool IsVerified => Verified.HasValue || PasswordReset.HasValue;
        public string ResetToken { get; set; }
        public DateTime? ResetTokenExpires { get; set; }
        public DateTime? PasswordReset { get; set; }
        public DateTime Created { get; set; }
        public DateTime? Updated { get; set; }
        public String Facebook { get; set; }
        public String Cinema { get; set; }
        public String Weather { get; set; }
        public String News { get; set; }
        public String Covid { get; set; }
        public String Paypal { get; set; }
        public String Imgur { get; set; }

        //Facebook widgets
        public String FacebookList { get; set; }
        public String FacebookProfil { get; set; }
        public String FacebookPost { get; set; }

        //Imgur wigets
        public String ImgurFavorite { get; set; }
        public String ImgurFeed { get; set; }
        public String ImgurProfil { get; set; }

        //Spotify widgets
        public String SpotifyMusic { get; set; }
        public String SpotifyUser { get; set; }
        public String SpotifyPlaylist { get; set; }

        public List<RefreshToken> RefreshTokens { get; set; }

        public bool OwnsToken(string token) 
        {
            return this.RefreshTokens?.Find(x => x.Token == token) != null;
        }
    }
}