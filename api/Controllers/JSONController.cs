using Api.Models;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.IO;
using System.Net;
using System.Net.Sockets;
using WebApi.Class;

namespace WebApi.Controllers
{
    public class JSONController : Controller
    {
        [HttpGet("about.json")]
        public string About()
        {
            Json json = new Json();
            json.Client = new JsonClient();
            json.Client.Host = GetPublicIP();
            json.Client.Local = GetLocalIPAddress();

            json.Server = new JsonServer();
            json.Server.CurrentTime = (Int64)(DateTime.UtcNow.Subtract(new DateTime(1970, 1, 1))).TotalSeconds;

            json.Server.Services = new List<JsonService>();

            foreach (var Service in Startup.Services)
            {
                JsonService service = new JsonService();
                service.Name = Service.Name;
                service.Description = Service.Description;
                service.Widgets = new List<JsonWidget>();

                json.Server.Services.Add(service);

                foreach (var Widget in Service.Widgets)
                {
                    JsonWidget widget = new JsonWidget();
                    widget.Name = Widget.Name;
                    widget.Description = Widget.Description;

                    service.Widgets.Add(widget);
                }
            }
            return JsonConvert.SerializeObject(json, Formatting.Indented);
        }

        public static string GetPublicIP()
        {
            string myPublicIp = "";
            WebRequest request = WebRequest.Create("https://api.ipify.org/");
            using (WebResponse response = request.GetResponse())
            using (StreamReader stream = new StreamReader(response.GetResponseStream()))
            {
                myPublicIp = stream.ReadToEnd();
            }
            return myPublicIp;
        }

        public static string GetLocalIPAddress()
        {
            var host = Dns.GetHostEntry(Dns.GetHostName());
            foreach (var ip in host.AddressList)
            {
                if (ip.AddressFamily == AddressFamily.InterNetwork)
                {
                    return ip.ToString();
                }
            }
            throw new Exception("No network adapters with an IPv4 address in the system");
        }
    }
}
