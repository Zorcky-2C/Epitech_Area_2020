using Microsoft.AspNetCore.Mvc;
using System.ComponentModel.DataAnnotations;

namespace WebApi.Models.Services
{
    public class accesstokenRequest
    {
        [FromHeader]
        [Required]
        public string Authorization { get; set; }
    }
}
