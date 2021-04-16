using System.ComponentModel.DataAnnotations;

namespace WebApi.Models.Services
{
    public class HomePageRequest
    {
        [Required]
        public string authorization { get; set; }

        [Required]
        public string userAgent { get; set; }

    }
}
