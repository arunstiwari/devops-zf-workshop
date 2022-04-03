FROM ubuntu:20.04
LABEL maintainer=arunstiwari@gmail.com
LABEL version=1.0
LABEL evironment=dev

RUN apt-get update
RUN apt install nginx -y

ENV VERSION 1.0
ENV PATH $PATH:/usr/local/myapp/bin

# ENTRYPOINT [ "echo", "Hello" ]
CMD ["env"]
