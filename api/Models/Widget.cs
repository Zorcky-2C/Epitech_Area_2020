using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Api.Models
{
    public class Widget
    {
        public string Name;
        public string Description;

        public Widget(string name, string description)
        {
            Name = name;
            Description = description;
        }
    }
}
