﻿// <auto-generated />
using System;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Infrastructure;
using Microsoft.EntityFrameworkCore.Storage.ValueConversion;
using WebApi.Helpers;

namespace WebApi.Migrations
{
    [DbContext(typeof(DataContext))]
    partial class DataContextModelSnapshot : ModelSnapshot
    {
        protected override void BuildModel(ModelBuilder modelBuilder)
        {
#pragma warning disable 612, 618
            modelBuilder
                .HasAnnotation("ProductVersion", "3.1.5");

            modelBuilder.Entity("WebApi.Entities.Account", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("INTEGER");

                    b.Property<DateTime>("Created")
                        .HasColumnType("TEXT");

                    b.Property<string>("Email")
                        .HasColumnType("TEXT");

                    b.Property<string>("FirstName")
                        .HasColumnType("TEXT");

                    b.Property<string>("LastName")
                        .HasColumnType("TEXT");

                    b.Property<string>("PasswordHash")
                        .HasColumnType("TEXT");

                    b.Property<DateTime?>("PasswordReset")
                        .HasColumnType("TEXT");

                    b.Property<string>("ResetToken")
                        .HasColumnType("TEXT");

                    b.Property<DateTime?>("ResetTokenExpires")
                        .HasColumnType("TEXT");

                    b.Property<DateTime?>("Updated")
                        .HasColumnType("TEXT");

                    b.Property<string>("VerificationToken")
                        .HasColumnType("TEXT");

                    b.Property<DateTime?>("Verified")
                        .HasColumnType("TEXT");

                    b.Property<string>("Facebook")
                        .HasColumnType("TEXT");

                    b.Property<string>("Paypal")
                        .HasColumnType("TEXT");

                    b.Property<string>("Cinema")
                        .HasColumnType("TEXT");

                    b.Property<string>("Weather")
                        .HasColumnType("TEXT");

                    b.Property<string>("New")
                        .HasColumnType("TEXT");

                    b.Property<string>("Covid")
                        .HasColumnType("TEXT");

                    b.Property<string>("Imgur")
                        .HasColumnType("TEXT");

                    b.Property<string>("FacebookList")
                        .HasColumnType("TEXT");
                    b.Property<string>("FacebookProfil")
                        .HasColumnType("TEXT");
                    b.Property<string>("FacebookPost")
                        .HasColumnType("TEXT");

                    b.Property<string>("ImgurFavorite")
                        .HasColumnType("TEXT");
                    b.Property<string>("ImgurFeed")
                        .HasColumnType("TEXT");
                    b.Property<string>("ImgurProfil")
                        .HasColumnType("TEXT");

                    b.Property<string>("SpotifyMusic")
                        .HasColumnType("TEXT");
                    b.Property<string>("SpotifyUser")
                        .HasColumnType("TEXT");
                    b.Property<string>("SpotifyPlaylist")
                        .HasColumnType("TEXT");

                    b.HasKey("Id");

                    b.ToTable("Accounts");
                });

            modelBuilder.Entity("WebApi.Entities.Account", b =>
                {
                    b.OwnsMany("WebApi.Entities.RefreshToken", "RefreshTokens", b1 =>
                        {
                            b1.Property<int>("Id")
                                .ValueGeneratedOnAdd()
                                .HasColumnType("INTEGER");

                            b1.Property<int>("AccountId")
                                .HasColumnType("INTEGER");

                            b1.Property<DateTime>("Created")
                                .HasColumnType("TEXT");

                            b1.Property<string>("CreatedByIp")
                                .HasColumnType("TEXT");

                            b1.Property<DateTime>("Expires")
                                .HasColumnType("TEXT");

                            b1.Property<string>("ReplacedByToken")
                                .HasColumnType("TEXT");

                            b1.Property<DateTime?>("Revoked")
                                .HasColumnType("TEXT");

                            b1.Property<string>("RevokedByIp")
                                .HasColumnType("TEXT");

                            b1.Property<string>("Token")
                                .HasColumnType("TEXT");

                            b1.HasKey("Id");

                            b1.HasIndex("AccountId");

                            b1.ToTable("RefreshToken");

                            b1.WithOwner("Account")
                                .HasForeignKey("AccountId");
                        });
                });
#pragma warning restore 612, 618
        }
    }
}