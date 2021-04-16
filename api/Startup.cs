using Api.Models;
using AutoMapper;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using System;
using System.Collections.Generic;
using WebApi.Helpers;
using WebApi.Middleware;
using WebApi.Services;

namespace WebApi
{
    public class Startup
    {
        public IConfiguration Configuration { get; }

        public static List<Service> Services = new List<Service>();

        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public void ConfigureServices(IServiceCollection services)
        {
            services.AddDbContext<DataContext>();
            services.AddCors();
            services.AddControllers().AddJsonOptions(x => x.JsonSerializerOptions.IgnoreNullValues = true);
            services.AddAutoMapper(AppDomain.CurrentDomain.GetAssemblies());
            services.AddSwaggerGen();

            services.Configure<AppSettings>(Configuration.GetSection("AppSettings"));

            services.AddScoped<IAccountService, AccountService>();
            services.AddScoped<IEmailService, EmailService>();

            services.AddScoped<IImgurService, ImgurService>();
            services.AddScoped<ISpotifyService, SpotifyService>();
            services.AddScoped<IWeatherService, WeatherService>();
            services.AddScoped<ICovidService, CovidService>();
            services.AddScoped<ICinemaService, CinemaService>();
            services.AddScoped<IYoutubeService, YoutubeService>();

            var serviceFacebook = new Service("Facebook", "Allows you to get information about your Facebook account.");
            var serviceImgur = new Service("Imgur", "Allows you to get quickly search for images on Imgur servers.");
            var serviceSpotify = new Service("Spotify", "Listen to Spotify musics.");

            serviceFacebook.Widgets.Add(new Widget("Friend List", "View your friend list."));
            serviceFacebook.Widgets.Add(new Widget("Post and Share", "Post a Facebook status to your profile."));
            serviceFacebook.Widgets.Add(new Widget("Profil", "View profil information."));

            serviceImgur.Widgets.Add(new Widget("Feed", "View your personal feed."));
            serviceImgur.Widgets.Add(new Widget("Favorite", "View your favorites image."));
            serviceImgur.Widgets.Add(new Widget("Profil", "View profil information."));

            serviceSpotify.Widgets.Add(new Widget("Search a Music", "Search for your favorite song."));
            serviceSpotify.Widgets.Add(new Widget("Playlists", "Manage your playlists by categories."));
            serviceSpotify.Widgets.Add(new Widget("Search an User", "Search for a Spotify user."));

            Services.Add(serviceFacebook);
            Services.Add(serviceImgur);
            Services.Add(serviceSpotify);
        }

        public void Configure(IApplicationBuilder app, IWebHostEnvironment env, DataContext context)
        {
            context.Database.Migrate();

            app.UseSwagger();
            app.UseSwaggerUI(x => x.SwaggerEndpoint("/swagger/v1/swagger.json", "ASP.NET Core 3.1 Dashboard API"));

            app.UseRouting();

            app.UseCors(x => x
                .SetIsOriginAllowed(origin => true)
                .AllowAnyMethod()
                .AllowAnyHeader()
                .AllowCredentials());

            app.UseMiddleware<ErrorHandlerMiddleware>();

            app.UseMiddleware<JwtMiddleware>();

            app.UseEndpoints(x => x.MapControllers());
        }
    }
}
