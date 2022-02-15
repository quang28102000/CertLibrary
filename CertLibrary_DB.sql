USE [master]
GO
/****** Object:  Database [CertLibrary]    Script Date: 2/15/2022 8:25:16 PM ******/
CREATE DATABASE [CertLibrary]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'CertLibrary', FILENAME = N'D:\CN6\OJT\FIN3\CertLibrary.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'CertLibrary_log', FILENAME = N'D:\CN6\OJT\FIN3\CertLibrary_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [CertLibrary] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [CertLibrary].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [CertLibrary] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [CertLibrary] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [CertLibrary] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [CertLibrary] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [CertLibrary] SET ARITHABORT OFF 
GO
ALTER DATABASE [CertLibrary] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [CertLibrary] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [CertLibrary] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [CertLibrary] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [CertLibrary] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [CertLibrary] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [CertLibrary] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [CertLibrary] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [CertLibrary] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [CertLibrary] SET  DISABLE_BROKER 
GO
ALTER DATABASE [CertLibrary] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [CertLibrary] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [CertLibrary] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [CertLibrary] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [CertLibrary] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [CertLibrary] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [CertLibrary] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [CertLibrary] SET RECOVERY FULL 
GO
ALTER DATABASE [CertLibrary] SET  MULTI_USER 
GO
ALTER DATABASE [CertLibrary] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [CertLibrary] SET DB_CHAINING OFF 
GO
ALTER DATABASE [CertLibrary] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [CertLibrary] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [CertLibrary] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [CertLibrary] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'CertLibrary', N'ON'
GO
ALTER DATABASE [CertLibrary] SET QUERY_STORE = OFF
GO
USE [CertLibrary]
GO
/****** Object:  Table [dbo].[course]    Script Date: 2/15/2022 8:25:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[course](
	[course_id] [int] IDENTITY(1,1) NOT NULL,
	[course_tittle] [nvarchar](256) NOT NULL,
	[platform] [nvarchar](256) NOT NULL,
	[category] [nvarchar](256) NOT NULL,
 CONSTRAINT [PK_course] PRIMARY KEY CLUSTERED 
(
	[course_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[course_detail]    Script Date: 2/15/2022 8:25:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[course_detail](
	[course_detail_id] [int] IDENTITY(1,1) NOT NULL,
	[image] [nchar](256) NULL,
	[course_id] [int] NOT NULL,
	[course_length] [float] NULL,
 CONSTRAINT [PK_course_detail] PRIMARY KEY CLUSTERED 
(
	[course_detail_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[course_employee]    Script Date: 2/15/2022 8:25:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[course_employee](
	[course_id] [int] NOT NULL,
	[employee_id] [int] NOT NULL,
	[status] [int] NOT NULL,
	[start_date] [date] NULL,
	[end_date] [date] NULL,
	[cert_link] [nchar](256) NULL,
	[isDeleted] [bit] NULL,
 CONSTRAINT [PK_course_employee] PRIMARY KEY CLUSTERED 
(
	[course_id] ASC,
	[employee_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[course_skills]    Script Date: 2/15/2022 8:25:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[course_skills](
	[c_skill_id] [int] IDENTITY(1,1) NOT NULL,
	[skill_name] [nvarchar](45) NOT NULL,
	[course_id] [int] NOT NULL,
 CONSTRAINT [PK_course_skills] PRIMARY KEY CLUSTERED 
(
	[c_skill_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[employee]    Script Date: 2/15/2022 8:25:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[employee](
	[employee_id] [int] IDENTITY(1,1) NOT NULL,
	[full_name] [nvarchar](45) NOT NULL,
	[user_name] [nchar](45) NOT NULL,
	[password] [nchar](45) NOT NULL,
	[email] [nchar](45) NULL,
	[profile_image] [nchar](256) NULL,
 CONSTRAINT [PK_employee] PRIMARY KEY CLUSTERED 
(
	[employee_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[employee_skills]    Script Date: 2/15/2022 8:25:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[employee_skills](
	[e_skill_id] [int] IDENTITY(1,1) NOT NULL,
	[skill_name] [nvarchar](45) NOT NULL,
	[employee_id] [int] NOT NULL,
 CONSTRAINT [PK_employee_skills] PRIMARY KEY CLUSTERED 
(
	[e_skill_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[course] ON 

INSERT [dbo].[course] ([course_id], [course_tittle], [platform], [category]) VALUES (1, N'The Complete SQL Bootcamp 2022: Go from Zero to Hero', N'Udemy', N'SQL')
INSERT [dbo].[course] ([course_id], [course_tittle], [platform], [category]) VALUES (2, N'Web Design: Wireframes to Prototypes', N'Coursera', N'Web Design')
INSERT [dbo].[course] ([course_id], [course_tittle], [platform], [category]) VALUES (3, N'Explore All Data Science', N'CodeCademy', N'Data')
INSERT [dbo].[course] ([course_id], [course_tittle], [platform], [category]) VALUES (4, N'CS50''s Introduction to Game Development', N'EdX', N'Game programming')
INSERT [dbo].[course] ([course_id], [course_tittle], [platform], [category]) VALUES (5, N'Basic Angular 4x Programming', N'Edumall', N'Angular')
INSERT [dbo].[course] ([course_id], [course_tittle], [platform], [category]) VALUES (6, N'The MERN Fullstack Guide', N'Udemy', N'Fullstack')
INSERT [dbo].[course] ([course_id], [course_tittle], [platform], [category]) VALUES (7, N'Node with React: Fullstack Web Development', N'Udemy', N'Fullstack')
INSERT [dbo].[course] ([course_id], [course_tittle], [platform], [category]) VALUES (8, N'Full Stack Web Development with Angular', N'Coursera', N'Fullstack')
INSERT [dbo].[course] ([course_id], [course_tittle], [platform], [category]) VALUES (9, N'Connecting Front-End to Back-End', N'CodeCademy', N'Fullstack')
INSERT [dbo].[course] ([course_id], [course_tittle], [platform], [category]) VALUES (10, N'Spring Boot Tutorial | Spring Boot Full Stack with React.js', N'Udemy', N'Fullstack')
INSERT [dbo].[course] ([course_id], [course_tittle], [platform], [category]) VALUES (11, N'The Complete 2022 Web Development Bootcamp', N'Udemy', N'Fullstack')
SET IDENTITY_INSERT [dbo].[course] OFF
GO
SET IDENTITY_INSERT [dbo].[course_detail] ON 

INSERT [dbo].[course_detail] ([course_detail_id], [image], [course_id], [course_length]) VALUES (1, N'https://coursemarks.com/wp-content/uploads/2020/11/762616_7693_3.jpg.webp                                                                                                                                                                                       ', 1, 8.51)
INSERT [dbo].[course_detail] ([course_detail_id], [image], [course_id], [course_length]) VALUES (2, N'https://s3.amazonaws.com/coursera_assets/meta_images/generated/XDP/XDP~COURSE!~web-design-wireframes-prototypes/XDP~COURSE!~web-design-wireframes-prototypes.jpeg                                                                                               ', 2, 41)
INSERT [dbo].[course_detail] ([course_detail_id], [image], [course_id], [course_length]) VALUES (3, N'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTnKSKdmkM60RHg2l6ngoMsHbEUvmOsLR3fmaNgXPYgeWuKjWLUG3xW2TTh7WiW9rFNiqc&usqp=CAU                                                                                                                            ', 3, 20)
INSERT [dbo].[course_detail] ([course_detail_id], [image], [course_id], [course_length]) VALUES (4, N'https://aseanop.com/wp-content/uploads/2020/05/CS50s-Introduction-to-Game-Development.jpg                                                                                                                                                                       ', 4, 25)
INSERT [dbo].[course_detail] ([course_detail_id], [image], [course_id], [course_length]) VALUES (5, N'https://www.zendvn.com/img/upload/courses/desktop_97dc1df2d48219e2f70a285b66307ca9fbf7bf6c.jpeg                                                                                                                                                                 ', 5, 21)
INSERT [dbo].[course_detail] ([course_detail_id], [image], [course_id], [course_length]) VALUES (6, N'https://www.filepicker.io/api/file/TMGv8UTlyDnfF5jKec0w                                                                                                                                                                                                         ', 6, 23)
INSERT [dbo].[course_detail] ([course_detail_id], [image], [course_id], [course_length]) VALUES (7, N'https://www.gangboard.com/wp-content/uploads/2020/02/node-with-react-fullstack-web-development-course.jpg                                                                                                                                                       ', 7, 30)
INSERT [dbo].[course_detail] ([course_detail_id], [image], [course_id], [course_length]) VALUES (8, N'https://i.ytimg.com/vi/eRZHzi3l-4Q/mqdefault.jpg                                                                                                                                                                                                                ', 8, 28)
INSERT [dbo].[course_detail] ([course_detail_id], [image], [course_id], [course_length]) VALUES (9, N'https://connect.vn/wp-content/uploads/2019/12/front-end-back-end-l%C3%A0-g%C3%AC.png                                                                                                                                                                            ', 9, 29)
INSERT [dbo].[course_detail] ([course_detail_id], [image], [course_id], [course_length]) VALUES (10, N'https://miro.medium.com/max/1200/1*NqhTGwmPUYzCwBcYq5fDSQ.jpeg                                                                                                                                                                                                  ', 10, 27)
INSERT [dbo].[course_detail] ([course_detail_id], [image], [course_id], [course_length]) VALUES (11, N'https://i0.wp.com/tutorialsplanet.net/wp-content/uploads/2020/06/455553363.jpg?fit=750%2C422&ssl=1                                                                                                                                                              ', 11, NULL)
SET IDENTITY_INSERT [dbo].[course_detail] OFF
GO
INSERT [dbo].[course_employee] ([course_id], [employee_id], [status], [start_date], [end_date], [cert_link], [isDeleted]) VALUES (1, 1, 2, CAST(N'2021-03-22' AS Date), CAST(N'2021-04-30' AS Date), N'https://coursera.org/share/e068ba156767852577c088171de5c304                                                                                                                                                                                                     ', 0)
INSERT [dbo].[course_employee] ([course_id], [employee_id], [status], [start_date], [end_date], [cert_link], [isDeleted]) VALUES (1, 4, 2, CAST(N'2021-09-23' AS Date), CAST(N'2021-10-08' AS Date), N'https://coursera.org/share/e068ba156767852577c088171de5c304                                                                                                                                                                                                     ', 0)
INSERT [dbo].[course_employee] ([course_id], [employee_id], [status], [start_date], [end_date], [cert_link], [isDeleted]) VALUES (1, 20, 1, CAST(N'2022-02-15' AS Date), NULL, NULL, 0)
INSERT [dbo].[course_employee] ([course_id], [employee_id], [status], [start_date], [end_date], [cert_link], [isDeleted]) VALUES (2, 3, 1, CAST(N'2022-01-20' AS Date), NULL, NULL, 0)
INSERT [dbo].[course_employee] ([course_id], [employee_id], [status], [start_date], [end_date], [cert_link], [isDeleted]) VALUES (2, 11, 2, CAST(N'2022-01-02' AS Date), CAST(N'2022-01-19' AS Date), N'https://coursera.org/share/e068ba156767852577c088171de5c304                                                                                                                                                                                                     ', 0)
INSERT [dbo].[course_employee] ([course_id], [employee_id], [status], [start_date], [end_date], [cert_link], [isDeleted]) VALUES (2, 15, 1, CAST(N'2022-02-10' AS Date), NULL, NULL, 0)
INSERT [dbo].[course_employee] ([course_id], [employee_id], [status], [start_date], [end_date], [cert_link], [isDeleted]) VALUES (2, 20, 2, CAST(N'2021-11-29' AS Date), CAST(N'2021-12-27' AS Date), N'https://coursera.org/share/e068ba156767852577c088171de5c304                                                                                                                                                                                                     ', 0)
INSERT [dbo].[course_employee] ([course_id], [employee_id], [status], [start_date], [end_date], [cert_link], [isDeleted]) VALUES (3, 4, 3, CAST(N'2019-08-21' AS Date), CAST(N'2019-09-12' AS Date), NULL, 1)
INSERT [dbo].[course_employee] ([course_id], [employee_id], [status], [start_date], [end_date], [cert_link], [isDeleted]) VALUES (3, 7, 1, CAST(N'2022-02-01' AS Date), NULL, NULL, 0)
INSERT [dbo].[course_employee] ([course_id], [employee_id], [status], [start_date], [end_date], [cert_link], [isDeleted]) VALUES (3, 15, 4, CAST(N'2021-12-30' AS Date), CAST(N'2022-01-20' AS Date), NULL, 0)
INSERT [dbo].[course_employee] ([course_id], [employee_id], [status], [start_date], [end_date], [cert_link], [isDeleted]) VALUES (4, 2, 2, CAST(N'2020-12-26' AS Date), CAST(N'2021-01-15' AS Date), N'https://coursera.org/share/e068ba156767852577c088171de5c304                                                                                                                                                                                                     ', 0)
INSERT [dbo].[course_employee] ([course_id], [employee_id], [status], [start_date], [end_date], [cert_link], [isDeleted]) VALUES (4, 11, 1, CAST(N'2022-02-02' AS Date), NULL, NULL, 0)
INSERT [dbo].[course_employee] ([course_id], [employee_id], [status], [start_date], [end_date], [cert_link], [isDeleted]) VALUES (5, 1, 1, CAST(N'2022-01-30' AS Date), NULL, NULL, 0)
INSERT [dbo].[course_employee] ([course_id], [employee_id], [status], [start_date], [end_date], [cert_link], [isDeleted]) VALUES (5, 5, 4, CAST(N'2022-01-01' AS Date), CAST(N'2011-01-30' AS Date), NULL, 0)
INSERT [dbo].[course_employee] ([course_id], [employee_id], [status], [start_date], [end_date], [cert_link], [isDeleted]) VALUES (5, 20, 3, CAST(N'2022-01-22' AS Date), CAST(N'2022-02-10' AS Date), NULL, 0)
INSERT [dbo].[course_employee] ([course_id], [employee_id], [status], [start_date], [end_date], [cert_link], [isDeleted]) VALUES (6, 2, 4, CAST(N'2021-10-25' AS Date), CAST(N'2021-11-02' AS Date), NULL, 0)
INSERT [dbo].[course_employee] ([course_id], [employee_id], [status], [start_date], [end_date], [cert_link], [isDeleted]) VALUES (6, 10, 2, CAST(N'2021-12-23' AS Date), CAST(N'2022-01-17' AS Date), N'https://coursera.org/share/e068ba156767852577c088171de5c304                                                                                                                                                                                                     ', 1)
INSERT [dbo].[course_employee] ([course_id], [employee_id], [status], [start_date], [end_date], [cert_link], [isDeleted]) VALUES (6, 15, 2, CAST(N'2021-01-10' AS Date), CAST(N'2021-02-23' AS Date), N'https://coursera.org/share/e068ba156767852577c088171de5c304                                                                                                                                                                                                     ', 0)
INSERT [dbo].[course_employee] ([course_id], [employee_id], [status], [start_date], [end_date], [cert_link], [isDeleted]) VALUES (7, 1, 3, CAST(N'2021-11-28' AS Date), CAST(N'2021-12-28' AS Date), NULL, 0)
INSERT [dbo].[course_employee] ([course_id], [employee_id], [status], [start_date], [end_date], [cert_link], [isDeleted]) VALUES (7, 10, 3, CAST(N'2022-01-12' AS Date), CAST(N'2022-01-28' AS Date), NULL, 0)
INSERT [dbo].[course_employee] ([course_id], [employee_id], [status], [start_date], [end_date], [cert_link], [isDeleted]) VALUES (7, 20, 4, CAST(N'2022-01-01' AS Date), CAST(N'2022-02-02' AS Date), NULL, 0)
INSERT [dbo].[course_employee] ([course_id], [employee_id], [status], [start_date], [end_date], [cert_link], [isDeleted]) VALUES (8, 6, 1, CAST(N'2022-02-12' AS Date), NULL, NULL, 0)
INSERT [dbo].[course_employee] ([course_id], [employee_id], [status], [start_date], [end_date], [cert_link], [isDeleted]) VALUES (8, 15, 1, CAST(N'2020-08-15' AS Date), CAST(N'2020-10-22' AS Date), NULL, 0)
INSERT [dbo].[course_employee] ([course_id], [employee_id], [status], [start_date], [end_date], [cert_link], [isDeleted]) VALUES (9, 7, 1, CAST(N'2022-02-15' AS Date), NULL, NULL, 0)
INSERT [dbo].[course_employee] ([course_id], [employee_id], [status], [start_date], [end_date], [cert_link], [isDeleted]) VALUES (9, 15, 2, CAST(N'2022-01-10' AS Date), CAST(N'2022-01-30' AS Date), N'https://coursera.org/share/e068ba156767852577c088171de5c304                                                                                                                                                                                                     ', 0)
INSERT [dbo].[course_employee] ([course_id], [employee_id], [status], [start_date], [end_date], [cert_link], [isDeleted]) VALUES (11, 7, 2, CAST(N'2022-01-02' AS Date), CAST(N'2022-01-30' AS Date), N'https://coursera.org/share/e068ba156767852577c088171de5c304                                                                                                                                                                                                     ', 0)
GO
SET IDENTITY_INSERT [dbo].[course_skills] ON 

INSERT [dbo].[course_skills] ([c_skill_id], [skill_name], [course_id]) VALUES (1, N'SQL', 1)
INSERT [dbo].[course_skills] ([c_skill_id], [skill_name], [course_id]) VALUES (2, N'Web Design', 2)
INSERT [dbo].[course_skills] ([c_skill_id], [skill_name], [course_id]) VALUES (3, N'Data', 3)
INSERT [dbo].[course_skills] ([c_skill_id], [skill_name], [course_id]) VALUES (4, N'Machine learning', 3)
INSERT [dbo].[course_skills] ([c_skill_id], [skill_name], [course_id]) VALUES (5, N'Unity', 4)
INSERT [dbo].[course_skills] ([c_skill_id], [skill_name], [course_id]) VALUES (6, N'Angular', 5)
INSERT [dbo].[course_skills] ([c_skill_id], [skill_name], [course_id]) VALUES (7, N'Mango DB', 6)
INSERT [dbo].[course_skills] ([c_skill_id], [skill_name], [course_id]) VALUES (8, N'Express JS', 6)
INSERT [dbo].[course_skills] ([c_skill_id], [skill_name], [course_id]) VALUES (9, N'React JS', 6)
INSERT [dbo].[course_skills] ([c_skill_id], [skill_name], [course_id]) VALUES (10, N'Node JS', 6)
INSERT [dbo].[course_skills] ([c_skill_id], [skill_name], [course_id]) VALUES (11, N'Node JS', 7)
INSERT [dbo].[course_skills] ([c_skill_id], [skill_name], [course_id]) VALUES (12, N'React JS', 7)
INSERT [dbo].[course_skills] ([c_skill_id], [skill_name], [course_id]) VALUES (13, N'HTML', 8)
INSERT [dbo].[course_skills] ([c_skill_id], [skill_name], [course_id]) VALUES (14, N'CSS', 8)
INSERT [dbo].[course_skills] ([c_skill_id], [skill_name], [course_id]) VALUES (15, N'HTML', 8)
INSERT [dbo].[course_skills] ([c_skill_id], [skill_name], [course_id]) VALUES (16, N'JSP', 8)
INSERT [dbo].[course_skills] ([c_skill_id], [skill_name], [course_id]) VALUES (17, N'Servlet', 8)
INSERT [dbo].[course_skills] ([c_skill_id], [skill_name], [course_id]) VALUES (18, N'HTML', 9)
INSERT [dbo].[course_skills] ([c_skill_id], [skill_name], [course_id]) VALUES (19, N'CSS', 9)
INSERT [dbo].[course_skills] ([c_skill_id], [skill_name], [course_id]) VALUES (20, N'Servlet', 9)
INSERT [dbo].[course_skills] ([c_skill_id], [skill_name], [course_id]) VALUES (21, N'Spring boot', 10)
INSERT [dbo].[course_skills] ([c_skill_id], [skill_name], [course_id]) VALUES (22, N'Bootstrap', 10)
INSERT [dbo].[course_skills] ([c_skill_id], [skill_name], [course_id]) VALUES (23, N'Bootstrap', 11)
INSERT [dbo].[course_skills] ([c_skill_id], [skill_name], [course_id]) VALUES (24, N'Spring boot', 11)
INSERT [dbo].[course_skills] ([c_skill_id], [skill_name], [course_id]) VALUES (25, N'HTML', 11)
INSERT [dbo].[course_skills] ([c_skill_id], [skill_name], [course_id]) VALUES (26, N'Servlet', 11)
SET IDENTITY_INSERT [dbo].[course_skills] OFF
GO
SET IDENTITY_INSERT [dbo].[employee] ON 

INSERT [dbo].[employee] ([employee_id], [full_name], [user_name], [password], [email], [profile_image]) VALUES (1, N'Nguyễn Văn A', N'ANV1                                         ', N'Ahuygw435                                    ', N'ANV1@fsoft.com.vn                            ', N'https://camerabox.vn/uploads/news/2018_06/den-gan-chu-the.jpg                                                                                                                                                                                                   ')
INSERT [dbo].[employee] ([employee_id], [full_name], [user_name], [password], [email], [profile_image]) VALUES (2, N'Nguyễn Việt Á', N'ANV2                                         ', N'Gsgrwe345                                    ', N'ANV2@fsoft.com.vn                            ', N'https://photocross.net/wp-content/uploads/2020/03/anh-chan-dung.jpg                                                                                                                                                                                             ')
INSERT [dbo].[employee] ([employee_id], [full_name], [user_name], [password], [email], [profile_image]) VALUES (3, N'Nguyễn Thị Bé', N'BeNT                                         ', N'VG13HHhh                                     ', N'BeNT@fsoft.com.vn                            ', N'https://aphoto.vn/wp-content/uploads/2019/07/anh-chan-dung-nghe-thuat-top-aphoto5.jpg                                                                                                                                                                           ')
INSERT [dbo].[employee] ([employee_id], [full_name], [user_name], [password], [email], [profile_image]) VALUES (4, N'Nguyễn Thị Cam', N'CamNT                                        ', N'dsh243uh                                     ', N'CamNT@fsoft.com.vn                           ', N'https://www.paratime.vn/wp-content/uploads/2019/09/timestudio.vn-headshot-eye-glasses-02.jpg                                                                                                                                                                    ')
INSERT [dbo].[employee] ([employee_id], [full_name], [user_name], [password], [email], [profile_image]) VALUES (5, N'Lê Hữu Định', N'DinhLH                                       ', N'dsfnhu3728                                   ', N'DinhLH@fsoft.com.vn                          ', N'https://zstudio.vn/wp-content/uploads/2018/05/chup-anh-chan-dung-nam-5.jpg                                                                                                                                                                                      ')
INSERT [dbo].[employee] ([employee_id], [full_name], [user_name], [password], [email], [profile_image]) VALUES (6, N'Phạm Luôn Đúng', N'DungPL                                       ', N'hui3ih34u2                                   ', N'DungPL@fsoft.com.vn                          ', N'https://chupanhvn.s3.ap-southeast-1.amazonaws.com/wp-content/uploads/2020/10/25114511/chan-dung-nam2.jpg                                                                                                                                                        ')
INSERT [dbo].[employee] ([employee_id], [full_name], [user_name], [password], [email], [profile_image]) VALUES (7, N'Lưu Thị Dung', N'DungLT                                       ', N'huhui8927                                    ', N'DungLT@fsoft.com.vn                          ', N'https://chupanhvn.s3.ap-southeast-1.amazonaws.com/wp-content/uploads/2017/04/23050109/chup-anh-chan-dung-930x620.jpg                                                                                                                                            ')
INSERT [dbo].[employee] ([employee_id], [full_name], [user_name], [password], [email], [profile_image]) VALUES (10, N'Trần Ngọc Dung', N'DungTN                                       ', N'euhwfw2786                                   ', N'DungTN@fsoft.com.vn                          ', N'https://digitalphoto.com.vn/wp-content/uploads/2018/08/40710181061_e790d61992_o-1-683x1024.jpg                                                                                                                                                                  ')
INSERT [dbo].[employee] ([employee_id], [full_name], [user_name], [password], [email], [profile_image]) VALUES (11, N'Nguyễn Thị Nam Em', N'EmNTN                                        ', N'IUHhuiiu387                                  ', N'EmNTN@fsoft.com.vn                           ', N'https://vcdn-giaitri.vnecdn.net/2019/01/20/Nam-Em-2-3142-1548001119.jpg                                                                                                                                                                                         ')
INSERT [dbo].[employee] ([employee_id], [full_name], [user_name], [password], [email], [profile_image]) VALUES (12, N'Hoàng Văn Hoàng', N'HoangHV                                      ', N'jhwqiu278                                    ', N'HoangHV@fsoft.com.vn                         ', N'https://photo-cms-baophapluat.zadn.vn/w800/Uploaded/2022/bpivpvoi/2020_11_03/1_8_zvmk.jpg                                                                                                                                                                       ')
INSERT [dbo].[employee] ([employee_id], [full_name], [user_name], [password], [email], [profile_image]) VALUES (13, N'Kim Soo Huyn', N'HuynKS                                       ', N'hhyiu7823                                    ', N'HuynKS@.fsoft.com.vn                         ', N'https://file.vfo.vn/hinh/2018/02/nhung-hinh-chan-dung-dep-nhat-cach-chup-tao-dang-hinh-chup-chan-dung-29.jpg                                                                                                                                                    ')
INSERT [dbo].[employee] ([employee_id], [full_name], [user_name], [password], [email], [profile_image]) VALUES (14, N'Lê Văn Luyện', N'LuyenLV                                      ', N'HJyguygtiu78                                 ', N'LuyenLV@fsoft.com.vn                         ', N'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRnCOL1fWyrEEzXPP2Uql6WbI04fXrtrwrL4w&usqp=CAU                                                                                                                                                             ')
INSERT [dbo].[employee] ([employee_id], [full_name], [user_name], [password], [email], [profile_image]) VALUES (15, N'Nguyễn Thị Ngọc Liên', N'LienNTN                                      ', N'Lyiuh728                                     ', N'LienNTN@fsoft.com.vn                         ', N'https://img.websosanh.vn/v10/users/review/images/xp5exn8diygzs/1551160483586_5370080.jpg?compress=85                                                                                                                                                            ')
INSERT [dbo].[employee] ([employee_id], [full_name], [user_name], [password], [email], [profile_image]) VALUES (16, N'Nguyễn Văn Minh', N'MinhNV                                       ', N'jiuh787                                      ', N'MinhNV@fsoft.com.vn                          ', N'https://www.chapter3d.com/wp-content/uploads/2020/06/doi-mat-biet-noi-1.jpg                                                                                                                                                                                     ')
INSERT [dbo].[employee] ([employee_id], [full_name], [user_name], [password], [email], [profile_image]) VALUES (17, N'Đinh Hoàng Oanh', N'OanhDH                                       ', N'hiu727                                       ', N'OanhDH@fsoft.com.vn                          ', N'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQvOyrudxWFpYnbIz-XBHxWNMSAtb8FKPc0SOnE1PTrGqnDGlwquraHLMrJIKaHppVDPd8&usqp=CAU                                                                                                                            ')
INSERT [dbo].[employee] ([employee_id], [full_name], [user_name], [password], [email], [profile_image]) VALUES (18, N'Lê Hồng Phúc', N'PhucLH                                       ', N'jiuh782                                      ', N'PhucLH@fsoft.com.vn                          ', N'https://i.pinimg.com/236x/eb/d6/8c/ebd68cc71d8db7b0fe1052994fa2e608.jpg                                                                                                                                                                                         ')
INSERT [dbo].[employee] ([employee_id], [full_name], [user_name], [password], [email], [profile_image]) VALUES (19, N'Nguyễn Thị Lệ Quyên', N'QuyenNTL                                     ', N'uyguy86                                      ', N'QuyenNTL@fsoft.com.vn                        ', N'https://vcdn-vnexpress.vnecdn.net/2022/02/09/le-quyen-3428-1629359203-2723-1644389467.jpg                                                                                                                                                                       ')
INSERT [dbo].[employee] ([employee_id], [full_name], [user_name], [password], [email], [profile_image]) VALUES (20, N'Nguyễn Sai', N'SaiN                                         ', N'Huyguy88                                     ', N'SaiN@fsoft.com.vn                            ', N'https://static.wikia.nocookie.net/naruto/images/e/ee/.................png/revision/latest?cb=20170823110922&path-prefix=vi                                                                                                                                      ')
INSERT [dbo].[employee] ([employee_id], [full_name], [user_name], [password], [email], [profile_image]) VALUES (22, N'Trương Yến', N'YenT                                         ', N'Hhgt78                                       ', N'YenT@fsoft.com.vn                            ', N'https://thuthuatnhanh.com/wp-content/uploads/2021/11/Anh-chan-dung-dep.jpg                                                                                                                                                                                      ')
INSERT [dbo].[employee] ([employee_id], [full_name], [user_name], [password], [email], [profile_image]) VALUES (23, N'Hoàng Thượng', N'ThuongH                                      ', N'huuyg786                                     ', N'ThuongH@fsoft.com.vn                         ', N'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQoEUXyRXz7Y_F0CmxiGkB3Z-rfoX7l5oCyRA&usqp=CAU                                                                                                                                                             ')
SET IDENTITY_INSERT [dbo].[employee] OFF
GO
SET IDENTITY_INSERT [dbo].[employee_skills] ON 

INSERT [dbo].[employee_skills] ([e_skill_id], [skill_name], [employee_id]) VALUES (1, N'SQL', 1)
INSERT [dbo].[employee_skills] ([e_skill_id], [skill_name], [employee_id]) VALUES (2, N'CSS', 1)
INSERT [dbo].[employee_skills] ([e_skill_id], [skill_name], [employee_id]) VALUES (3, N'HTML', 1)
INSERT [dbo].[employee_skills] ([e_skill_id], [skill_name], [employee_id]) VALUES (4, N'Spring boot', 1)
INSERT [dbo].[employee_skills] ([e_skill_id], [skill_name], [employee_id]) VALUES (5, N'Unity', 2)
INSERT [dbo].[employee_skills] ([e_skill_id], [skill_name], [employee_id]) VALUES (6, N'.net', 2)
INSERT [dbo].[employee_skills] ([e_skill_id], [skill_name], [employee_id]) VALUES (7, N'Web Design', 3)
INSERT [dbo].[employee_skills] ([e_skill_id], [skill_name], [employee_id]) VALUES (8, N'Logo Design', 3)
INSERT [dbo].[employee_skills] ([e_skill_id], [skill_name], [employee_id]) VALUES (9, N'Game Design', 4)
INSERT [dbo].[employee_skills] ([e_skill_id], [skill_name], [employee_id]) VALUES (10, N'Servlet', 5)
INSERT [dbo].[employee_skills] ([e_skill_id], [skill_name], [employee_id]) VALUES (11, N'HTML', 5)
INSERT [dbo].[employee_skills] ([e_skill_id], [skill_name], [employee_id]) VALUES (12, N'Game Design', 7)
INSERT [dbo].[employee_skills] ([e_skill_id], [skill_name], [employee_id]) VALUES (13, N'Logo Design', 7)
INSERT [dbo].[employee_skills] ([e_skill_id], [skill_name], [employee_id]) VALUES (14, N'HTML', 10)
INSERT [dbo].[employee_skills] ([e_skill_id], [skill_name], [employee_id]) VALUES (15, N'CSS', 10)
INSERT [dbo].[employee_skills] ([e_skill_id], [skill_name], [employee_id]) VALUES (16, N'Bootstrap', 10)
INSERT [dbo].[employee_skills] ([e_skill_id], [skill_name], [employee_id]) VALUES (17, N'JSP', 11)
INSERT [dbo].[employee_skills] ([e_skill_id], [skill_name], [employee_id]) VALUES (18, N'Servlet', 11)
INSERT [dbo].[employee_skills] ([e_skill_id], [skill_name], [employee_id]) VALUES (19, N'Web Design', 11)
INSERT [dbo].[employee_skills] ([e_skill_id], [skill_name], [employee_id]) VALUES (20, N'Unity', 12)
INSERT [dbo].[employee_skills] ([e_skill_id], [skill_name], [employee_id]) VALUES (21, N'Game Design', 13)
INSERT [dbo].[employee_skills] ([e_skill_id], [skill_name], [employee_id]) VALUES (22, N'Data', 14)
INSERT [dbo].[employee_skills] ([e_skill_id], [skill_name], [employee_id]) VALUES (24, N'SQL', 17)
INSERT [dbo].[employee_skills] ([e_skill_id], [skill_name], [employee_id]) VALUES (25, N'Spring boot', 18)
INSERT [dbo].[employee_skills] ([e_skill_id], [skill_name], [employee_id]) VALUES (26, N'CSS', 18)
INSERT [dbo].[employee_skills] ([e_skill_id], [skill_name], [employee_id]) VALUES (27, N'Unity', 19)
INSERT [dbo].[employee_skills] ([e_skill_id], [skill_name], [employee_id]) VALUES (28, N'SQL', 20)
INSERT [dbo].[employee_skills] ([e_skill_id], [skill_name], [employee_id]) VALUES (29, N'HTML', 20)
INSERT [dbo].[employee_skills] ([e_skill_id], [skill_name], [employee_id]) VALUES (30, N'Bootstrap', 20)
INSERT [dbo].[employee_skills] ([e_skill_id], [skill_name], [employee_id]) VALUES (31, N'Unity', 20)
SET IDENTITY_INSERT [dbo].[employee_skills] OFF
GO
ALTER TABLE [dbo].[course_detail]  WITH CHECK ADD  CONSTRAINT [FK_course_detail_course] FOREIGN KEY([course_id])
REFERENCES [dbo].[course] ([course_id])
GO
ALTER TABLE [dbo].[course_detail] CHECK CONSTRAINT [FK_course_detail_course]
GO
ALTER TABLE [dbo].[course_employee]  WITH CHECK ADD  CONSTRAINT [FK_course_employee_course] FOREIGN KEY([course_id])
REFERENCES [dbo].[course] ([course_id])
GO
ALTER TABLE [dbo].[course_employee] CHECK CONSTRAINT [FK_course_employee_course]
GO
ALTER TABLE [dbo].[course_employee]  WITH CHECK ADD  CONSTRAINT [FK_course_employee_employee] FOREIGN KEY([employee_id])
REFERENCES [dbo].[employee] ([employee_id])
GO
ALTER TABLE [dbo].[course_employee] CHECK CONSTRAINT [FK_course_employee_employee]
GO
ALTER TABLE [dbo].[course_skills]  WITH CHECK ADD  CONSTRAINT [FK_course_skills_course] FOREIGN KEY([course_id])
REFERENCES [dbo].[course] ([course_id])
GO
ALTER TABLE [dbo].[course_skills] CHECK CONSTRAINT [FK_course_skills_course]
GO
ALTER TABLE [dbo].[employee_skills]  WITH CHECK ADD  CONSTRAINT [FK_employee_skills_employee] FOREIGN KEY([employee_id])
REFERENCES [dbo].[employee] ([employee_id])
GO
ALTER TABLE [dbo].[employee_skills] CHECK CONSTRAINT [FK_employee_skills_employee]
GO
USE [master]
GO
ALTER DATABASE [CertLibrary] SET  READ_WRITE 
GO
