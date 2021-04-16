using System.ComponentModel.DataAnnotations;

namespace WebApi.Models.Services
{
    public class removePlaylistRequest
    {
        [Required]
        public string authorization { get; set; }
        [Required]
        public string id { get; set; }
        [Required]
        public string param { get; set; }
    }
}
