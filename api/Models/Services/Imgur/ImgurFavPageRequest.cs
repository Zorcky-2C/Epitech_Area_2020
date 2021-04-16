using System.ComponentModel.DataAnnotations;

namespace WebApi.Models.Services
{
    public class FavPageRequest
    {
        [Required]
        public string authorization { get; set; }

        [Required]
        public string userAgent { get; set; }
        [Required]
        public string username { get; set; }
        [Required]
        public string param { get; set; }

    }
}
