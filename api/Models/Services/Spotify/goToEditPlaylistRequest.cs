using System.ComponentModel.DataAnnotations;

namespace WebApi.Models.Services
{
    public class goToEditPlaylistRequest
    {
        [Required]
        public string authorization { get; set; }
        [Required]
        public string playlistId { get; set; }
        [Required]
        public string param { get; set; }
    }
}
