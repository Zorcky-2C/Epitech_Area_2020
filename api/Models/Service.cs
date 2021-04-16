using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Api.Models
{
    public class Service
    {
        public string Name;
        public string Description;
        public List<Widget> Widgets;

        public Service(string name, string description)
        {
            Name = name;
            Description = description;
            Widgets = new List<Widget>();
        }

        public Widget GetWidget(string widgetname)
        {
            foreach (var widget in Widgets)
            {
                if (widget.Name == widgetname)
                {
                    return widget;
                }
            }
            return null;
        }
    }
}
