package br.com.helpusz.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;

@Service
public class S3Service {
  private final S3Client s3Client;
  private final String bucketName;
  private final String region;

  public S3Service(S3Client s3Client,
		@Value("${aws.s3.bucket-name}")
		String bucketName,

		@Value("${aws.s3.region}")
		String region) {

    this.s3Client = s3Client;
    this.bucketName = bucketName;
    this.region = region;
  }

  public String uploadFile(MultipartFile file, String folder) throws IOException {
    String fileName = folder + "/" + file.getOriginalFilename();

    PutObjectRequest putObjectRequest = PutObjectRequest.builder()
			.bucket(bucketName)
			.key(fileName)
			.contentType(file.getContentType())
			.build();

    s3Client.putObject(putObjectRequest, software.amazon.awssdk.core.sync.RequestBody.fromBytes(file.getBytes()));

    return getFileUrl(fileName);
  }

  public String getFileUrl(String fileName) {
    return String.format("https://%s.s3.%s.amazonaws.com/%s", bucketName, region, fileName);
  }
}
