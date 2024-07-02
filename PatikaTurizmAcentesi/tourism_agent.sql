PGDMP  )    &                |            tourism_agent    16.3    16.3 <    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
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
          public          postgres    false    221            �            1259    41249    reservations    TABLE       CREATE TABLE public.reservations (
    id integer NOT NULL,
    room_id integer NOT NULL,
    guest_name character varying(50) NOT NULL,
    guest_surname character varying(50) NOT NULL,
    guest_identity character varying(20) NOT NULL,
    start_date date NOT NULL,
    end_date date NOT NULL,
    total_price numeric(10,2) NOT NULL,
    email text,
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
    pension_type character varying(255)
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
    public          postgres    false    220   &N       �          0    41176    hotels 
   TABLE DATA           T   COPY public.hotels (id, name, address, email, phone, star_rating, city) FROM stdin;
    public          postgres    false    218   �O       �          0    41199    pension_types 
   TABLE DATA           ;   COPY public.pension_types (id, hotel_id, type) FROM stdin;
    public          postgres    false    222   TQ       �          0    41249    reservations 
   TABLE DATA           �  COPY public.reservations (id, room_id, guest_name, guest_surname, guest_identity, start_date, end_date, total_price, email, customer_name, customer_nationality, customer_identity_num, customer_phone, customer_email, guest_nationality, child_name, child_nationality, child_identity_num, child2_name, child2_nationality, child2_identity_num, hotel_name, reservation_note, pension_type) FROM stdin;
    public          postgres    false    228   R       �          0    41224    rooms 
   TABLE DATA           �   COPY public.rooms (id, hotel_id, season_id, pension_type_id, room_type, price_per_night_adult, price_per_night_child, stock, features) FROM stdin;
    public          postgres    false    226   �R       �          0    41212    seasons 
   TABLE DATA           W   COPY public.seasons (id, hotel_id, start_date, end_date, name, multiplier) FROM stdin;
    public          postgres    false    224   �T       �          0    41168    users 
   TABLE DATA           L   COPY public.users (id, username, password, role, name, surname) FROM stdin;
    public          postgres    false    216   �U                  0    0    hotel_features_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.hotel_features_id_seq', 114, true);
          public          postgres    false    219                       0    0    hotels_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.hotels_id_seq', 14, true);
          public          postgres    false    217                       0    0    pension_types_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.pension_types_id_seq', 38, true);
          public          postgres    false    221                       0    0    reservations_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.reservations_id_seq', 4, true);
          public          postgres    false    227                       0    0    rooms_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.rooms_id_seq', 30, true);
          public          postgres    false    225                       0    0    seasons_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.seasons_id_seq', 31, true);
          public          postgres    false    223            	           0    0    users_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.users_id_seq', 9, true);
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
       public          postgres    false    4679    218    224            �   �  x�m�;j1��ZZŬ ��Q�q瀋Hc�$v���Z��t�X��JW���7��+�d��Ô�y������>���˸�Fz��]�Ӱ�_��37(�q9�yV鸤�[��iI_��t<�i�Hܡ힟���?*3l���.M�q��LwΗ�� =Z-��dD�%3BI�B)fzAW�(�A��"�rh��#E��*"��U�_�D�Z!��hT�bVh��Qh�\O�|W4��HDaU��Fah^��QxU���Vx�DޢQ�w��qz�"0)z�X��"�dnDT��t��L�L�,t,�U�N�hD�($�QG��%�F�E�U.��7N�*"��UxĢ���6�e��7���}q�H6�htu���O�� ����9�g���      �   �  x�}RKN�0];���
�~�젭��RĊ�4�U*'A	�\�@�����HV"{�g�<',�.�ΐ䕄)	Z3���*Q4ȸzҗ��m<l�֏S-Y<��kp�MX�D#vKr�U��p�K0E�Bc����Tg��������"�w��+�I���1��%*�DU\k��s�ң3�-���P�l,34z�#H�Ц<���-�_��-О%�A6)2;Y��#+-ִ�u[�W�`3m	�0�(��|�^޶������@�<k��1����$f�g2Xj_o��.)<s�'���x��x�"S*$�Q��ãsv-��7Y�!�˦/��A���o��=�8s�{���yq�?CHv�n�h#��@�
�h��9�N'��yw��=����<���%�B�      �   �   x�u�;
1���fY�L��cz����&����Ap5��=�<S搴���Eb�ȵlk֧yտ���Ǽ<����r����g.������u�@4��k3��>7��Q���F,lF� ��X��X/S����@<C�0����c�pF�����J�?�6��      �   �   x���=�@����$��nZ�:����ʁ��N�@�V�ıx�@��0�&�S ���:_�DO�M�sk`�J"�X�CL��7���?v�ܥ~8����k*�&�#�}{ك*��J�����S<�HAaֵ��r�S��RZ�HF��ۤ�Q�-�Ӱ��9響>��懯��.��x -�      �   �  x����j�@���S����]wJ�Ѝ[l���BZ�Py�<X�9�6n���Ł���o��?�L�U���_W�%_p��P�o�L�4��ۺ����1/�uZ_5���6i�A+��xɊ�;��V�����6M~�|�v���ʦ*:辧Eӷ_�6���t{�7�=�P;/�Cխ��TFGD���#U�HOLSQ��3�j;�(���T;r1=v�S,�L��H	Nc@��tjHN�#�	89M�iB�Qj)����ז��b��Iv�mUq\x,z��i��X-�f`�Q5ܕUŢ�U������4T�U��UL(-IV]�Y<�:0JʬjgI%��V1�4)����K�V2*�a��e���ն�)�ո
+N�X���4���J����A�cҴ:�+&FVC<�EH�X9;X%`�w $�u&i�*��;��b��IʤJgW ���qWy�q���+��7�9���������-��mqc��_�F�o�o��� �_}L      �   �   x���1�0 g�|���4m���$�
U�C�D��o`���%MJ��rNg�63d��i�Y�B���myֈ�`�ӈ����^?�m���s�QE܇���ȗFn�g��|Y��}9�"_�y�+�Fp^H��`����@.3Z(d`N\��M���T�0�h��&�}�����*?�ẻ0�"��8翟%m���[�lG����>���V]Uv��k�i��#��N      �   [   x�3�LL����442���H.#�ĜL�\jnAN~ej*X ���49������n#.��Ԍ��N�v�@vbe"�9gY*�4�$������� �b0n     