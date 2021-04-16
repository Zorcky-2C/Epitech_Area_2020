using System.ComponentModel.DataAnnotations;

namespace WebApi.Models.Services
{
    public class FacebookRequest
    {
        [Required]
        public string accesstoken { get; set; }
    }
}
