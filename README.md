# VectorModelServiceTest

# 向量模型服务并发测试

## 服务信息
url: http://10.10.20.12:8000/embedding
method: POST
headers: {"Content-Type": "application/json;charset==utf-8"}
body:
{
"text": "姚明的女儿",
"type": "cloudy"
}

## 测试数据
文件：kms.eecjeggchc.txt
text规则：name+paths+chapterTitle+paragraphs
注意：
*  paths, chapterTitle, paragraphs有可能为空；
*  paths, paragraphs为list，拼接text时，用" " join；