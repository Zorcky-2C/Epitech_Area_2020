using System.ComponentModel.DataAnnotations;

namespace WebApi.Models.Services
{
    public class WeatherRequest
    {
        [Required]
        public string city { get; set; }
    }
}
