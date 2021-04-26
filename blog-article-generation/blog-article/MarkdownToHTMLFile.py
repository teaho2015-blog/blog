import codecs, markdown2

# 读取 markdown 文本
input_file = codecs.open("phone-arch/phone-arch.md", mode="r", encoding="utf-8")
text = input_file.read()

# 转为 html 文本
md = markdown2.Markdown()
html = md.convert(text)

# 保存为文件
output_file = codecs.open("phone-arch/phone-arch.html", mode="w", encoding="utf-8")
output_file.write(html)