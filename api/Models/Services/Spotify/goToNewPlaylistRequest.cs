using System.ComponentModel.DataAnnotations;

namespace WebApi.Models.Services
{
    public class goToNewPlaylistRequest
    {
        [Required]
        public string authorization { get; set; }
    }
}
