using System.ComponentModel.DataAnnotations;

namespace WebApi.Models.Services
{
    public class ImageUrlRequest
    {
        [Required]
        public string imageId { get; set; }

        [Required]
        public string imageExtention { get; set; }

    }
}
