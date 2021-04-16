using System.ComponentModel.DataAnnotations;

namespace WebApi.Models.Services
{
    public class searchSongsRequest
    {
        [Required]
        public string authorization { get; set; }
        [Required]
        public string name { get; set; }
        [Required]
        public string type { get; set; }
    }
}
