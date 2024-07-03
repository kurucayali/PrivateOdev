
Admin girişi için:
Kullanıcı adı: admin 
	şifre: 123

Employee girişi için 
Kullanıcı adı: ali 
	şifre: 123


Patika Turizm Acentesi Projesi
Bu proje, bir turizm acentesi için geliştirilmiş bir rezervasyon ve yönetim sistemidir. 
Java Swing kullanarak grafik kullanıcı arayüzü (GUI) oluşturulmuş ve PostgreSQL veritabanı kullanılarak veriler yönetilmiştir. 
Proje, otel yönetimi, oda yönetimi, rezervasyon yönetimi ve dönem yönetimi gibi işlevleri içermektedir.


Özellikler
Kullanıcı Yönetimi: Kullanıcı ekleme, listeleme, güncelleme ve silme işlemleri.
Otel Yönetimi: Otel ekleme, listeleme, güncelleme ve silme işlemleri.
Oda Yönetimi: Oda ekleme, listeleme, güncelleme ve silme işlemleri.
Rezervasyon Yönetimi: Rezervasyon yapma, arama, güncelleme ve silme işlemleri.
Dönem Yönetimi: Dönem ekleme, listeleme, güncelleme ve silme işlemleri.
Fiyatlandırma: Odaların fiyatlandırılması ve rezervasyon toplam tutarının hesaplanması.

Gereksinimler
Java 17
PostgreSQL 16
IntelliJ IDEA veya başka bir IDE
PostgreSQL JDBC Driver

Kurulum
Depoyu Klonlayın:
https://github.com/kurucayali/PrivateOdev/tree/main/PatikaTurizmAcentesi

PostgreSQL Veritabanını Kurun:

PostgreSQL'i indirip kurun.
tourism_aget adında bir veritabanı oluşturun.
tourism_agent.sql dosyasını restore ederek gerekli tabloları oluşturun.

Projeyi Açın:
IntelliJ IDEA veya başka bir IDE kullanarak projeyi açın.
PostgreSQL JDBC sürücüsünü projeye ekleyin.

Veritabanı Bağlantısını Yapılandırın:
DatabaseConnection sınıfında veritabanı bağlantı bilgilerini güncelleyin.
Kullanım

Proje Çalıştırma:

App sınıfını çalıştırarak uygulamayı başlatın.

Giriş:
Kullanıcı adı ve şifre ile giriş yaparak sisteme erişim sağlayın.

Modüller:

Otel Yönetimi, Oda Yönetimi, Rezervasyon Yönetimi ve Dönem Yönetimi modüllerini kullanarak ilgili işlemleri gerçekleştirin.

Mimari
Proje, katmanlı mimari kullanılarak geliştirilmiştir. 


Ana katmanlar:

Veri Katmanı (DAO): Veritabanı işlemlerinin yapıldığı sınıflar.
Servis Katmanı: İş mantığının uygulandığı sınıflar.
Kontrol Katmanı (Controller): Kullanıcı arayüzünden gelen taleplerin işlendiği sınıflar.
Görünüm Katmanı (View): Kullanıcı arayüzü sınıfları.


Veritabanı Yapısı
users: Kullanıcı bilgileri.
hotels: Otel bilgileri.
rooms: Oda bilgileri.
reservations: Rezervasyon bilgileri.
seasons: Dönem bilgileri.
pension_types: Pansiyon türleri.


Paket Yapısı
controller: Kontrol katmanı sınıfları.
dao: Veri erişim katmanı sınıfları.
db: Veritabanı bağlantı sınıfları.
model: Veri modeli sınıfları.
service: İş mantığı sınıfları.
view: Kullanıcı arayüzü sınıfları.


İletişim
Proje ile ilgili geri bildirimler veya sorular için lütfen kurucayali@gmail.com adresinden iletişime geçin.