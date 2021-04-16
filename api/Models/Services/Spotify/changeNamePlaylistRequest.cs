using System.ComponentModel.DataAnnotations;

namespace WebApi.Models.Services
{
    public class changeNamePlaylistRequest
    {
        [Required]
        public string authorization { get; set; }
        [Required]
        public string currentPlaylistId { get; set; }
        [Required]
        public string name { get; set; }
    }
}
