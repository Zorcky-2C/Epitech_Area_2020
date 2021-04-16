using System.ComponentModel.DataAnnotations;

namespace WebApi.Models.Services
{
    public class addNewPlaylistRequest
    {
        [Required]
        public string authorization { get; set; }
        [Required]
        public string userId { get; set; }
        [Required]
        public string param { get; set; }
        [Required]
        public string name { get; set; }
    }
}
