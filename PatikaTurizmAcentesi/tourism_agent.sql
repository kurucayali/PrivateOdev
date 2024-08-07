PGDMP  "                    |            tourism_agent    16.3    16.3 <    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    41166    tourism_agent    DATABASE     �   CREATE DATABASE tourism_agent WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1252';
    DROP DATABASE tourism_agent;
                postgres    false            �            1259    41186    hotel_features    TABLE     �  CREATE TABLE public.hotel_features (
    id integer NOT NULL,
    hotel_id integer NOT NULL,
    feature character varying(50) NOT NULL,
    CONSTRAINT hotel_features_feature_check CHECK (((feature)::text = ANY ((ARRAY['Ücretsiz Otopark'::character varying, 'Ücretsiz WiFi'::character varying, 'Yüzme Havuzu'::character varying, 'Fitness Center'::character varying, 'Hotel Concierge'::character varying, 'SPA'::character varying, '7/24 Oda Servisi'::character varying])::text[])))
);
 "   DROP TABLE public.hotel_features;
       public         heap    postgres    false            �            1259    41185    hotel_features_id_seq    SEQUENCE     �   CREATE SEQUENCE public.hotel_features_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.hotel_features_id_seq;
       public          postgres    false    220            �           0    0    hotel_features_id_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.hotel_features_id_seq OWNED BY public.hotel_features.id;
          public          postgres    false    219            �            1259    41176    hotels    TABLE     {  CREATE TABLE public.hotels (
    id integer NOT NULL,
    name character varying(100) NOT NULL,
    address text NOT NULL,
    email character varying(100) NOT NULL,
    phone character varying(15) NOT NULL,
    star_rating integer NOT NULL,
    city character varying(255) NOT NULL,
    CONSTRAINT hotels_star_rating_check CHECK (((star_rating >= 1) AND (star_rating <= 5)))
);
    DROP TABLE public.hotels;
       public         heap    postgres    false            �            1259    41175    hotels_id_seq    SEQUENCE     �   CREATE SEQUENCE public.hotels_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.hotels_id_seq;
       public          postgres    false    218            �           0    0    hotels_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.hotels_id_seq OWNED BY public.hotels.id;
          public          postgres    false    217            �            1259    41199    pension_types    TABLE     �  CREATE TABLE public.pension_types (
    id integer NOT NULL,
    hotel_id integer NOT NULL,
    type character varying(50) NOT NULL,
    CONSTRAINT pension_types_type_check CHECK (((type)::text = ANY ((ARRAY['Ultra Her şey Dahil'::character varying, 'Her şey Dahil'::character varying, 'Oda Kahvaltı'::character varying, 'Tam Pansiyon'::character varying, 'Yarım Pansiyon'::character varying, 'Sadece Yatak'::character varying, 'Alkol Hariç Full credit'::character varying])::text[])))
);
 !   DROP TABLE public.pension_types;
       public         heap    postgres    false            �            1259    41198    pension_types_id_seq    SEQUENCE     �   CREATE SEQUENCE public.pension_types_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.pension_types_id_seq;
       public          postgres    false    222            �           0    0    pension_types_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.pension_types_id_seq OWNED BY public.pension_types.id;
          public          postgres    false    221            �            1259    41249    reservations    TABLE     �  CREATE TABLE public.reservations (
    id integer NOT NULL,
    room_id integer NOT NULL,
    guest_name character varying(50),
    guest_identity character varying(20),
    start_date date NOT NULL,
    end_date date NOT NULL,
    total_price numeric(10,2) NOT NULL,
    customer_name character varying(255) NOT NULL,
    customer_nationality character varying(50) NOT NULL,
    customer_identity_num character varying(50) NOT NULL,
    customer_phone character varying(50) NOT NULL,
    customer_email character varying(255) NOT NULL,
    guest_nationality character varying(50),
    child_name character varying(255),
    child_nationality character varying(50),
    child_identity_num character varying(50),
    child2_name character varying(255),
    child2_nationality character varying(50),
    child2_identity_num character varying(50),
    hotel_name character varying(255) NOT NULL,
    reservation_note text,
    pension_type character varying(255),
    room_type text
);
     DROP TABLE public.reservations;
       public         heap    postgres    false            �            1259    41248    reservations_id_seq    SEQUENCE     �   CREATE SEQUENCE public.reservations_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.reservations_id_seq;
       public          postgres    false    228            �           0    0    reservations_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.reservations_id_seq OWNED BY public.reservations.id;
          public          postgres    false    227            �            1259    41224    rooms    TABLE     N  CREATE TABLE public.rooms (
    id integer NOT NULL,
    hotel_id integer NOT NULL,
    season_id integer NOT NULL,
    pension_type_id integer NOT NULL,
    room_type character varying(50) NOT NULL,
    price_per_night_adult numeric(10,2) NOT NULL,
    price_per_night_child numeric(10,2) NOT NULL,
    stock integer NOT NULL,
    features jsonb NOT NULL,
    CONSTRAINT rooms_room_type_check CHECK (((room_type)::text = ANY ((ARRAY['Single room'::character varying, 'Double room'::character varying, 'Junior suite room'::character varying, 'Suite room'::character varying])::text[])))
);
    DROP TABLE public.rooms;
       public         heap    postgres    false            �            1259    41223    rooms_id_seq    SEQUENCE     �   CREATE SEQUENCE public.rooms_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.rooms_id_seq;
       public          postgres    false    226                        0    0    rooms_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.rooms_id_seq OWNED BY public.rooms.id;
          public          postgres    false    225            �            1259    41212    seasons    TABLE     �   CREATE TABLE public.seasons (
    id integer NOT NULL,
    hotel_id integer NOT NULL,
    start_date date NOT NULL,
    end_date date NOT NULL,
    name character varying(255),
    multiplier numeric(3,2)
);
    DROP TABLE public.seasons;
       public         heap    postgres    false            �            1259    41211    seasons_id_seq    SEQUENCE     �   CREATE SEQUENCE public.seasons_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.seasons_id_seq;
       public          postgres    false    224                       0    0    seasons_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.seasons_id_seq OWNED BY public.seasons.id;
          public          postgres    false    223            �            1259    41168    users    TABLE     �  CREATE TABLE public.users (
    id integer NOT NULL,
    username character varying(50) NOT NULL,
    password character varying(50) NOT NULL,
    role character varying(10) NOT NULL,
    name character varying(50) NOT NULL,
    surname character varying(50) NOT NULL,
    CONSTRAINT users_role_check CHECK (((role)::text = ANY ((ARRAY['admin'::character varying, 'employee'::character varying])::text[])))
);
    DROP TABLE public.users;
       public         heap    postgres    false            �            1259    41167    users_id_seq    SEQUENCE     �   CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.users_id_seq;
       public          postgres    false    216                       0    0    users_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;
          public          postgres    false    215            :           2604    41189    hotel_features id    DEFAULT     v   ALTER TABLE ONLY public.hotel_features ALTER COLUMN id SET DEFAULT nextval('public.hotel_features_id_seq'::regclass);
 @   ALTER TABLE public.hotel_features ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    219    220    220            9           2604    41179 	   hotels id    DEFAULT     f   ALTER TABLE ONLY public.hotels ALTER COLUMN id SET DEFAULT nextval('public.hotels_id_seq'::regclass);
 8   ALTER TABLE public.hotels ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    217    218    218            ;           2604    41202    pension_types id    DEFAULT     t   ALTER TABLE ONLY public.pension_types ALTER COLUMN id SET DEFAULT nextval('public.pension_types_id_seq'::regclass);
 ?   ALTER TABLE public.pension_types ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    222    221    222            >           2604    41252    reservations id    DEFAULT     r   ALTER TABLE ONLY public.reservations ALTER COLUMN id SET DEFAULT nextval('public.reservations_id_seq'::regclass);
 >   ALTER TABLE public.reservations ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    228    227    228            =           2604    41227    rooms id    DEFAULT     d   ALTER TABLE ONLY public.rooms ALTER COLUMN id SET DEFAULT nextval('public.rooms_id_seq'::regclass);
 7   ALTER TABLE public.rooms ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    226    225    226            <           2604    41215 
   seasons id    DEFAULT     h   ALTER TABLE ONLY public.seasons ALTER COLUMN id SET DEFAULT nextval('public.seasons_id_seq'::regclass);
 9   ALTER TABLE public.seasons ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    224    223    224            8           2604    41171    users id    DEFAULT     d   ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);
 7   ALTER TABLE public.users ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    216    215    216            �          0    41186    hotel_features 
   TABLE DATA           ?   COPY public.hotel_features (id, hotel_id, feature) FROM stdin;
    public          postgres    false    220   �M       �          0    41176    hotels 
   TABLE DATA           T   COPY public.hotels (id, name, address, email, phone, star_rating, city) FROM stdin;
    public          postgres    false    218   �O       �          0    41199    pension_types 
   TABLE DATA           ;   COPY public.pension_types (id, hotel_id, type) FROM stdin;
    public          postgres    false    222   �Q       �          0    41249    reservations 
   TABLE DATA           �  COPY public.reservations (id, room_id, guest_name, guest_identity, start_date, end_date, total_price, customer_name, customer_nationality, customer_identity_num, customer_phone, customer_email, guest_nationality, child_name, child_nationality, child_identity_num, child2_name, child2_nationality, child2_identity_num, hotel_name, reservation_note, pension_type, room_type) FROM stdin;
    public          postgres    false    228   IR       �          0    41224    rooms 
   TABLE DATA           �   COPY public.rooms (id, hotel_id, season_id, pension_type_id, room_type, price_per_night_adult, price_per_night_child, stock, features) FROM stdin;
    public          postgres    false    226   �T       �          0    41212    seasons 
   TABLE DATA           W   COPY public.seasons (id, hotel_id, start_date, end_date, name, multiplier) FROM stdin;
    public          postgres    false    224   �V       �          0    41168    users 
   TABLE DATA           L   COPY public.users (id, username, password, role, name, surname) FROM stdin;
    public          postgres    false    216   �W                  0    0    hotel_features_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.hotel_features_id_seq', 133, true);
          public          postgres    false    219                       0    0    hotels_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.hotels_id_seq', 16, true);
          public          postgres    false    217                       0    0    pension_types_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.pension_types_id_seq', 43, true);
          public          postgres    false    221                       0    0    reservations_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.reservations_id_seq', 12, true);
          public          postgres    false    227                       0    0    rooms_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.rooms_id_seq', 31, true);
          public          postgres    false    225                       0    0    seasons_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.seasons_id_seq', 31, true);
          public          postgres    false    223            	           0    0    users_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.users_id_seq', 10, true);
          public          postgres    false    215            I           2606    41192 "   hotel_features hotel_features_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.hotel_features
    ADD CONSTRAINT hotel_features_pkey PRIMARY KEY (id);
 L   ALTER TABLE ONLY public.hotel_features DROP CONSTRAINT hotel_features_pkey;
       public            postgres    false    220            G           2606    41184    hotels hotels_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.hotels
    ADD CONSTRAINT hotels_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.hotels DROP CONSTRAINT hotels_pkey;
       public            postgres    false    218            K           2606    41205     pension_types pension_types_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.pension_types
    ADD CONSTRAINT pension_types_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.pension_types DROP CONSTRAINT pension_types_pkey;
       public            postgres    false    222            Q           2606    41254    reservations reservations_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.reservations
    ADD CONSTRAINT reservations_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.reservations DROP CONSTRAINT reservations_pkey;
       public            postgres    false    228            O           2606    41232    rooms rooms_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.rooms
    ADD CONSTRAINT rooms_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.rooms DROP CONSTRAINT rooms_pkey;
       public            postgres    false    226            M           2606    41217    seasons seasons_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.seasons
    ADD CONSTRAINT seasons_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.seasons DROP CONSTRAINT seasons_pkey;
       public            postgres    false    224            E           2606    41174    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    216            R           2606    41193 +   hotel_features hotel_features_hotel_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.hotel_features
    ADD CONSTRAINT hotel_features_hotel_id_fkey FOREIGN KEY (hotel_id) REFERENCES public.hotels(id);
 U   ALTER TABLE ONLY public.hotel_features DROP CONSTRAINT hotel_features_hotel_id_fkey;
       public          postgres    false    220    4679    218            S           2606    41206 )   pension_types pension_types_hotel_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.pension_types
    ADD CONSTRAINT pension_types_hotel_id_fkey FOREIGN KEY (hotel_id) REFERENCES public.hotels(id);
 S   ALTER TABLE ONLY public.pension_types DROP CONSTRAINT pension_types_hotel_id_fkey;
       public          postgres    false    222    218    4679            X           2606    41255 &   reservations reservations_room_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.reservations
    ADD CONSTRAINT reservations_room_id_fkey FOREIGN KEY (room_id) REFERENCES public.rooms(id);
 P   ALTER TABLE ONLY public.reservations DROP CONSTRAINT reservations_room_id_fkey;
       public          postgres    false    4687    226    228            U           2606    41233    rooms rooms_hotel_id_fkey    FK CONSTRAINT     z   ALTER TABLE ONLY public.rooms
    ADD CONSTRAINT rooms_hotel_id_fkey FOREIGN KEY (hotel_id) REFERENCES public.hotels(id);
 C   ALTER TABLE ONLY public.rooms DROP CONSTRAINT rooms_hotel_id_fkey;
       public          postgres    false    226    218    4679            V           2606    41243     rooms rooms_pension_type_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.rooms
    ADD CONSTRAINT rooms_pension_type_id_fkey FOREIGN KEY (pension_type_id) REFERENCES public.pension_types(id);
 J   ALTER TABLE ONLY public.rooms DROP CONSTRAINT rooms_pension_type_id_fkey;
       public          postgres    false    4683    226    222            W           2606    41238    rooms rooms_season_id_fkey    FK CONSTRAINT     }   ALTER TABLE ONLY public.rooms
    ADD CONSTRAINT rooms_season_id_fkey FOREIGN KEY (season_id) REFERENCES public.seasons(id);
 D   ALTER TABLE ONLY public.rooms DROP CONSTRAINT rooms_season_id_fkey;
       public          postgres    false    4685    224    226            T           2606    41218    seasons seasons_hotel_id_fkey    FK CONSTRAINT     ~   ALTER TABLE ONLY public.seasons
    ADD CONSTRAINT seasons_hotel_id_fkey FOREIGN KEY (hotel_id) REFERENCES public.hotels(id);
 G   ALTER TABLE ONLY public.seasons DROP CONSTRAINT seasons_hotel_id_fkey;
       public          postgres    false    4679    218    224            �   �  x�m�?N�0���>EN ~��#�Tu+R��RE@�ҴC��Q�z1~N&?{�������G����0��<ܺ�t�ُ�R�eX� ���nߩ�쯗�EZ��0��ܭ�qJ�tH�Ӕ����x���d��{~���Q�n���vi��A��q?�E���J$F���Z!f�&*���5Fyy�V*�Fb
cи�XĬ0�Jazѷ�ȋ"�1EDb
�и�bVX�C��$bC�u�g�7h��[$���+|�8�Z�#�}v�c		���#��k��O&8��y�x4&	HѸ#*�̈�C��Z��"�"ϊh�JE����Vx�YCc`��5��/�2��#��U�����V�Ѝk�(l�Zl
[]�Ua�������VO��-������w�Z�]������d�	U�M�'ħ��P���)�?���h      �   �  x��S�n�@>/O1����@�ͭ%���T�UO��aBV쏵@e�}��Bs��}�.�낣ԕ;�|�����F��+�R�
n���5��t�6;d	j�Au(d\���e�|�E�%[&�	,�Kp�2"�x�6d��
���GIp�;�:������/Ԇ��ZIA&=�������J6dH�n��_;h���˾�kG����|s���9O{�>�/aE�ߓ'v��w]9pm��i:[�n�!�{y�،�����;�cp6#N�-�(W(P����SM�N�d�����ãz.���A+K I�ǚ���(eI�j�@�Ec�*p�BW��̿N5�c��9'�4M�٤��5�ʶ�̀���Y��刞Z�e8��*gw���ڮ� [
�#��yA+-֌�ftN�y����t�����p,(�q�� ������K��0DV`sx2��a>� �VXjoZkwpۋ��EE �l`^      �   �   x�u�M
�0���9Aq���}�.�M�n*(X*O�3x{��K���A&�v�I��^��դ�T7�2�L�'�3�j�eV�~;r (1m�h�ܯ��DZ�&<��@�DZd��R���i�MD�"���H��Xq�a�$A�#)���Nw�����������qPJ����k      �   �  x���AO�0����i7��8iz]�!U�	�$.jhT;F�����]9��;�����84Ђ��J㪱���{ύHD���@�P�x,���ጋ�?`Q�5!)c���K��0�b�dgy%IT7��F�/r��=Á��4�����v�����,_�9�a��KM�Rə���r豴��QVt�\��)�����ㄓ�����ڬ�d����&l���DļU�Z�(AݠsT�囙{V�s���qN�/���8W���@CA��'���L΍u�=�?�t�0} �6�7v1�ɉ���/�_�8�c!�d��[5��,��.q�ӥ+qΞTǵvqL��4��>/泜'����BE�fbK�)j����6$M��,؋I�f_�/��D*'u��g%������zh7���A��БT�B�oFݶ9�qx�'���5�^��c}��T����{I���axa���k"&佂����w)�B��
�DÒ�ڶ�a���ֱ:@G�_[ꨥ��Di
昞����r�����>�	,��T�(�;r�.d�tK��d�?r�4d���ɸ��B���M�D�;��ٴ��2wӀ���n����L���\���-ԫ[Ӿ[� �Q����1�:��z��[      �     x�͗�J�@�ϛ�9�����=YD�^/����F�҇�;���ubm6�dtArX��������)&๬��u�U��䌟s�.�}$��ɒ�xS��Y�\e��'�|S竬Ρ�$��u�V�o���ۺz�WM�ŧlݸ�Ͷ-�EU6պ=)�g�l/��a���B�"��e[l:R��.r�ѣH��XP��fL���3��8R��P}��Z�S������).j��q�����/�]�T��*�nߪ���VǡR�J`��ڎ�SZ��PV��
d��ǡR�j`��J�*IV�]����C������T��o3J��ʆ�*1c�U���rY�`Y%R`��bJiKA���J�S>��m�&5k������^1�tJ��`y������_�`�b���� H���DR*��L=��8	K�Tl�. ��]��7��=���G���r��)� '�'>���>���qc��_JF�g�8��{��SE�3��x_{|J��t�WmYTu����y�*Σ(��騞      �   �   x���1�0 g�|���4m���$�
U�C�D��o`���%MJ��rNg�63d��i�Y�B���myֈ�`�ӈ����^?�m���s�QE܇���ȗFn�g��|Y��}9�"_�y�+�Fp^H��`����@.3Z(d`N\��M���T�0�h��&�}�����*?�ẻ0�"��8翟%m���[�lG����>���V]Uv��k�i��#��N      �   Y   x�3�LL����442���H.#�ĜL�\jnAN~ej*X ���49������n#.��Ԍ��N�v�@vbe"�9gY*��`�l� S1     