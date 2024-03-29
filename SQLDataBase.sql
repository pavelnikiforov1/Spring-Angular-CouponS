USE [master]
GO
/****** Object:  Database [CouponSpringProgect]    Script Date: 26/06/2019 15:03:57 ******/
CREATE DATABASE [CouponSpringProgect]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'CouponSpringProgect', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.SQLEXPRESS\MSSQL\DATA\CouponSpringProgect.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'CouponSpringProgect_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.SQLEXPRESS\MSSQL\DATA\CouponSpringProgect_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [CouponSpringProgect] SET COMPATIBILITY_LEVEL = 140
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [CouponSpringProgect].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [CouponSpringProgect] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [CouponSpringProgect] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [CouponSpringProgect] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [CouponSpringProgect] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [CouponSpringProgect] SET ARITHABORT OFF 
GO
ALTER DATABASE [CouponSpringProgect] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [CouponSpringProgect] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [CouponSpringProgect] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [CouponSpringProgect] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [CouponSpringProgect] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [CouponSpringProgect] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [CouponSpringProgect] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [CouponSpringProgect] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [CouponSpringProgect] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [CouponSpringProgect] SET  DISABLE_BROKER 
GO
ALTER DATABASE [CouponSpringProgect] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [CouponSpringProgect] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [CouponSpringProgect] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [CouponSpringProgect] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [CouponSpringProgect] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [CouponSpringProgect] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [CouponSpringProgect] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [CouponSpringProgect] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [CouponSpringProgect] SET  MULTI_USER 
GO
ALTER DATABASE [CouponSpringProgect] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [CouponSpringProgect] SET DB_CHAINING OFF 
GO
ALTER DATABASE [CouponSpringProgect] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [CouponSpringProgect] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [CouponSpringProgect] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [CouponSpringProgect] SET QUERY_STORE = OFF
GO
USE [CouponSpringProgect]
GO
USE [CouponSpringProgect]
GO
/****** Object:  Sequence [dbo].[hibernate_sequence]    Script Date: 26/06/2019 15:03:57 ******/
CREATE SEQUENCE [dbo].[hibernate_sequence] 
 AS [bigint]
 START WITH 1
 INCREMENT BY 1
 MINVALUE -9223372036854775808
 MAXVALUE 9223372036854775807
 CACHE 
GO
/****** Object:  Table [dbo].[company]    Script Date: 26/06/2019 15:03:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[company](
	[id] [int] NOT NULL,
	[email] [varchar](50) NOT NULL,
	[name] [varchar](25) NOT NULL,
	[password] [varchar](25) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [UK_niu8sfil2gxywcru9ah3r4ec5] UNIQUE NONCLUSTERED 
(
	[name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[coupon]    Script Date: 26/06/2019 15:03:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[coupon](
	[id] [int] NOT NULL,
	[amount] [int] NOT NULL,
	[end_date] [date] NOT NULL,
	[image] [varchar](8000) NOT NULL,
	[messege] [varchar](1000) NOT NULL,
	[price] [float] NOT NULL,
	[start_date] [date] NOT NULL,
	[title] [varchar](25) NOT NULL,
	[type] [int] NOT NULL,
	[company_id] [int] NULL,
 CONSTRAINT [PK__coupon__3213E83F17EFA1C5] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [UK_jcif0jyr9us159pk7yae5jhrx] UNIQUE NONCLUSTERED 
(
	[title] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[customer]    Script Date: 26/06/2019 15:03:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[customer](
	[id] [int] NOT NULL,
	[cust_name] [varchar](15) NOT NULL,
	[password] [varchar](15) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [UK_g4fiur2itsbsmvajb6h1ocxj2] UNIQUE NONCLUSTERED 
(
	[cust_name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[customer_coupon_list]    Script Date: 26/06/2019 15:03:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[customer_coupon_list](
	[customer_list_id] [int] NOT NULL,
	[coupon_list_id] [int] NOT NULL
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[coupon]  WITH CHECK ADD  CONSTRAINT [FKe2v6qnb3w90rekqrae28iiqhm] FOREIGN KEY([company_id])
REFERENCES [dbo].[company] ([id])
GO
ALTER TABLE [dbo].[coupon] CHECK CONSTRAINT [FKe2v6qnb3w90rekqrae28iiqhm]
GO
ALTER TABLE [dbo].[customer_coupon_list]  WITH CHECK ADD  CONSTRAINT [FK7h84ll2gyyii94agxqgv89ifh] FOREIGN KEY([coupon_list_id])
REFERENCES [dbo].[coupon] ([id])
GO
ALTER TABLE [dbo].[customer_coupon_list] CHECK CONSTRAINT [FK7h84ll2gyyii94agxqgv89ifh]
GO
ALTER TABLE [dbo].[customer_coupon_list]  WITH CHECK ADD  CONSTRAINT [FKkcsduiixcr7w54sdukmlniev3] FOREIGN KEY([customer_list_id])
REFERENCES [dbo].[customer] ([id])
GO
ALTER TABLE [dbo].[customer_coupon_list] CHECK CONSTRAINT [FKkcsduiixcr7w54sdukmlniev3]
GO
USE [master]
GO
ALTER DATABASE [CouponSpringProgect] SET  READ_WRITE 
GO
