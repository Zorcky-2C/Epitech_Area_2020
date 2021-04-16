using System.ComponentModel.DataAnnotations;

namespace WebApi.Models.Services
{
    public class ImgurDataRequest
    {
        [Required]
        public string username { get; set; }
        [Required]
        public string accessToken { get; set; }
        [Required]
        public string refreshToken { get; set; }
    }
}
