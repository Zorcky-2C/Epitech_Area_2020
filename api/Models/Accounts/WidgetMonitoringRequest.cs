using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.ComponentModel.DataAnnotations;

namespace WebApi.Models.Accounts
{
    public class WidgetMonitoringRequest
    {
        public String Facebook { get; set; }
        public String Cinema { get; set; }
        public String Weather { get; set; }
        public String News { get; set; }
        public String Covid { get; set; }
        public String Paypal { get; set; }
        public String Imgur { get; set; }
    }

    public class FBWidgetMonitoringRequest
    {
        public String FacebookList { get; set; }
        public String FacebookProfil { get; set; }
        public String FacebookPost { get; set; }
    }

    public class ImgurWidgetMonitoringRequest
    {
        public String ImgurFavorite { get; set; }
        public String ImgurFeed { get; set; }
        public String ImgurProfil { get; set; }
    }
    public class SpotifyWidgetMonitoringRequest
    {
        public String SpotifyMusic { get; set; }
        public String SpotifyUser { get; set; }
        public String SpotifyPlaylist { get; set; }
    }
}