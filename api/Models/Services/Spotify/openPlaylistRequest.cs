using System.ComponentModel.DataAnnotations;

namespace WebApi.Models.Services
{
    public class openPlaylistRequest
    {
        [Required]
        public string authorization { get; set; }
    }
}
