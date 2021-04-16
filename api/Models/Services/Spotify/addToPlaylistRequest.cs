using System.ComponentModel.DataAnnotations;

namespace WebApi.Models.Services
{
    public class addToPlaylistRequest
    {
        [Required]
        public string authorization { get; set; }
    }
}
