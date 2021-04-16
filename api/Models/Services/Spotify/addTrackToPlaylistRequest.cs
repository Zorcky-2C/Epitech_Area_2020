using System.ComponentModel.DataAnnotations;

namespace WebApi.Models.Services
{
    public class addTrackToPlaylistRequest
    {
        [Required]
        public string authorization { get; set; }
        [Required]
        public string currentPlaylistId { get; set; }
        [Required]
        public string param { get; set; }
        [Required]
        public string uris { get; set; }
    }
}