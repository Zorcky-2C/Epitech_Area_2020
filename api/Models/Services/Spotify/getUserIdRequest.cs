using System.ComponentModel.DataAnnotations;

namespace WebApi.Models.Services
{
    public class getUserIdRequest
    {
        [Required]
        public string authorization { get; set; }
    }
}
