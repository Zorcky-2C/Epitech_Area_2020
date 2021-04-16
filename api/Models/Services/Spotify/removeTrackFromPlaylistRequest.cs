using System.ComponentModel.DataAnnotations;

namespace WebApi.Models.Services
{
    public class removeTrackFromPlaylistRequest
    {
        [Required]
        public string authorization { get; set; }
        public string currentPlaylistId { get; set; }
        public string param { get; set; }
        public string tracks { get; set; }
    }
}
