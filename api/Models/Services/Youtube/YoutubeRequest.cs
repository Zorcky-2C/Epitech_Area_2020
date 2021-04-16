using System.ComponentModel.DataAnnotations;

namespace WebApi.Models.Services
{
    public class YoutubeRequest
    {
        [Required]
        public string query { get; set; }
    }
}
