USE [nvg]
GO
SET IDENTITY_INSERT [dbo].[status] ON 

INSERT [dbo].[status] ([id], [name]) VALUES (1, N'ACTIVE')
INSERT [dbo].[status] ([id], [name]) VALUES (2, N'INACTIVE')
INSERT [dbo].[status] ([id], [name]) VALUES (3, N'DELETED')
SET IDENTITY_INSERT [dbo].[status] OFF
GO
SET IDENTITY_INSERT [dbo].[user] ON 

INSERT [dbo].[user] ([id], [created_at], [first_name], [image], [last_name], [yob], [address_id], [status_id]) VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[user] ([id], [created_at], [first_name], [image], [last_name], [yob], [address_id], [status_id]) VALUES (2, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[user] ([id], [created_at], [first_name], [image], [last_name], [yob], [address_id], [status_id]) VALUES (3, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
SET IDENTITY_INSERT [dbo].[user] OFF
GO
SET IDENTITY_INSERT [dbo].[retail_counter] ON 

INSERT [dbo].[retail_counter] ([id], [name], [status_id]) VALUES (1, N'Thực phẩm', 1)
INSERT [dbo].[retail_counter] ([id], [name], [status_id]) VALUES (2, N'Trang trí', 2)
SET IDENTITY_INSERT [dbo].[retail_counter] OFF
GO
SET IDENTITY_INSERT [dbo].[category] ON 

INSERT [dbo].[category] ([id], [name], [retail_counter_id], [status_id]) VALUES (1, N'Rau củ', 1, 1)
SET IDENTITY_INSERT [dbo].[category] OFF
GO
SET IDENTITY_INSERT [dbo].[product] ON 

INSERT [dbo].[product] ([id], [image], [name], [category_id], [status_id]) VALUES (1, N'image/cuxu', N'Củ xu hào', 1, 2)
SET IDENTITY_INSERT [dbo].[product] OFF
GO
SET IDENTITY_INSERT [dbo].[role] ON 

INSERT [dbo].[role] ([id], [name]) VALUES (1, N'ROLE_ADMIN')
INSERT [dbo].[role] ([id], [name]) VALUES (2, N'ROLE_SELLER')
INSERT [dbo].[role] ([id], [name]) VALUES (3, N'ROLE_BUYER')
SET IDENTITY_INSERT [dbo].[role] OFF
GO
INSERT [dbo].[user_role] ([user_id], [role_id]) VALUES (1, 1)
INSERT [dbo].[user_role] ([user_id], [role_id]) VALUES (2, 2)
INSERT [dbo].[user_role] ([user_id], [role_id]) VALUES (3, 3)
GO
SET IDENTITY_INSERT [dbo].[account] ON 

INSERT [dbo].[account] ([id], [password], [username], [user_id]) VALUES (1, N'$2a$10$uaNdD2th1HCkCNxD4lK1teuJZASVYVeBJlruN2tsODKcaA2/bUkj.', N'admin', 1)
INSERT [dbo].[account] ([id], [password], [username], [user_id]) VALUES (2, N'$2a$10$21maFEMhtPzI0n10sEwr9OcNf45KeWbHNxCIBOQRQMRLVrnLFdCQi', N'seller', 2)
INSERT [dbo].[account] ([id], [password], [username], [user_id]) VALUES (3, N'$2a$10$eTGY3YBQxx0oN.J.7sChvexd6.UCUGPCCBihYRZkPt8d8I034nrfm', N'buyer', 3)
SET IDENTITY_INSERT [dbo].[account] OFF
GO
