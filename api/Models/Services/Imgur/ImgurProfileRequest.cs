using System.ComponentModel.DataAnnotations;

namespace WebApi.Models.Services
{
    public class ImageProfileRequest
    {
        [Required]
        public string authorization { get; set; }

        [Required]
        public string username { get; set; }

    }
}
