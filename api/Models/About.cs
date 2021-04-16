using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Api.Models
{
    public class Json
    {
        [JsonProperty("client")]
        public JsonClient Client { get; set; }

        [JsonProperty("server")]
        public JsonServer Server { get; set; }
    }

    public class JsonClient
    {
        [JsonProperty("public")]
        public string Host { get; set; }
        [JsonProperty("local")]
        public string Local { get; set; }
    }

    public class JsonServer
    {
        [JsonProperty("current_time")]
        public long CurrentTime { get; set; }

        [JsonProperty("services")]
        public List<JsonService> Services { get; set; }
    }

    public class JsonService
    {
        [JsonProperty("name")]
        public string Name { get; set; }
        [JsonProperty("description")]
        public string Description { get; set; }

        [JsonProperty("widgets")]
        public List<JsonWidget> Widgets { get; set; }
    }

    public class JsonWidget
    {
        [JsonProperty("name")]
        public string Name { get; set; }

        [JsonProperty("description")]
        public string Description { get; set; }
    }
}
