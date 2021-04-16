using System;
using System.Collections.Generic;
using System.Net;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using RestSharp;
using WebApi.Helpers;
using WebApi.Models.Services;

namespace WebApi.Services
{
    public interface IService
    {
        public string Name { get; set; }
        public string Description { get; set; }
    }
}