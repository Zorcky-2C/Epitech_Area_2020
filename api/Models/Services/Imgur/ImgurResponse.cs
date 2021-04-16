using System.Net;

namespace WebApi.Models.Services
{
    public class ImgurResponse
    {
        public string AccessToken { get; set; }
        public string RefreshToken { get; set; }
        public string Username { get; set; }
    }

}
